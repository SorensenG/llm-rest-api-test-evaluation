import json, os, subprocess, sys, tempfile, unittest
from pathlib import Path
sys.path.insert(0, str(Path(__file__).parent))
from ai_judge import parse_judgment

class JudgeTests(unittest.TestCase):
    def test_structured_response_is_accepted(self):
        obj={'q1':{},'q4':{},'justification':'evidence-based','evidence':['tests/x.java#t'],'confidence':'LOW','ambiguity':'PRESENT','needs_human_review':True}
        self.assertEqual(parse_judgment(json.dumps(obj))['confidence'],'LOW')
    def test_malformed_response_is_rejected(self):
        with self.assertRaises((ValueError,json.JSONDecodeError)): parse_judgment('{}')
    def test_dry_run_does_not_need_credentials(self):
        p=subprocess.run([sys.executable,'scripts/ai_judge.py','--sample-file','results/ai-review/manual-sample-40-balanced.csv','--dry-run'],capture_output=True,text=True,check=True)
        self.assertIn('"mode": "dry-run"',p.stdout)
    def test_execute_refuses_without_explicit_key_and_budget(self):
        env=os.environ.copy(); env.pop('ANTHROPIC_API_KEY',None); env.pop('AI_JUDGE_MAX_USD',None)
        p=subprocess.run([sys.executable,'scripts/ai_judge.py','--sample-file','results/ai-review/manual-sample-40-balanced.csv','--execute'],capture_output=True,text=True,env=env)
        self.assertNotEqual(p.returncode,0); self.assertIn('refusing network call',p.stderr)
if __name__=='__main__': unittest.main()
