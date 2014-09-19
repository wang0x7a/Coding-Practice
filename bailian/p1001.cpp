#include <iostream>
#include <vector>

using namespace std;

vector<int> multply(vector<int> a, vector<int> b);
vector<int> pow_(vector<int> base, int expnt);

int main() {
  string s;
  int n;

  while (cin >> s >> n) {
    vector<int> number;
    int dot_idx = s.find(".");
    int rev_dot_pos = s.size() - 1 - dot_idx;
    string int_part = s.substr(0, dot_idx);
    string dec_part = s.substr(dot_idx + 1, s.size() - dot_idx - 1);
    if (int_part.compare("0") == 0)
      s = dec_part;
    else
      s = int_part + dec_part;

    cout << s << endl;

    for (int i = s.size() - 1; i >= 0; i--)
      number.push_back(s[i] - '0');

    number = pow_(number, n);

    int rev_dot_pos_new = rev_dot_pos * n;
    //int dot_pos_new     = number.size() - rev_dot_pos_new - 1;
    int dot_pos_new     = number.size() - rev_dot_pos_new;
    int cnt = 0;
    int i = number.size() - 1;
    string res;
    while (cnt < number.size() + 1) {
      if (cnt == dot_pos_new)
        res += ".";
      else
        res += number[i--];

      cnt++;
    }


    cout << res;
    /*
    for (int i = number.size() - 1; i >= 0; i--)
      cout << number[i];
    */

    cout << endl;
  }
}

vector<int> multiply(vector<int> a, vector<int> b) {
  vector<int> res(a.size() + b.size(), 0);

  for (int i = 0; i < a.size(); i++)
    for (int j = 0; j < b.size(); j++)
      res[i + j] += a[i] * b[j];

  for (int i = 0; i < res.size() - 1; i++) {
    res[i + 1] += res[i] / 10;
    res[i] %= 10;
  }

  while (!res.empty() && res.back() == 0)
    res.pop_back();

  if (res.empty())
    res.push_back(0);

  return res;
}

vector<int> pow_(vector<int> base, int expnt) {
  vector<int> res = vector<int>(1, 1);

  while (expnt) {
    if (expnt & 1) res = multiply(res, base);
    base = multiply(base, base);
    expnt >>= 1;
  }

  return res;
}
