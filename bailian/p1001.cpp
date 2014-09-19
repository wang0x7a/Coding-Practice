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

    if (dot_idx == -1) {

      for (int i = s.size() - 1; i >= 0; i--)
        number.push_back(s[i] - '0');

      number = pow_(number, n);

      for (int i = number.size() - 1; i >= 0; i--)
        cout << number[i];

      cout << endl;

    }
    else {
      int rev_dot_pos = s.size() - 1 - dot_idx;
      string int_part = s.substr(0, dot_idx);
      string dec_part = s.substr(dot_idx + 1, s.size() - dot_idx - 1);
      if (int_part.compare("0") == 0)
        s = dec_part;
      else
        s = int_part + dec_part;

      for (int i = s.size() - 1; i >= 0; i--)
        number.push_back(s[i] - '0');

      number = pow_(number, n);

      int rev_dot_pos_new = rev_dot_pos * n;
      //int dot_pos_new     = number.size() - rev_dot_pos_new - 1;
      if (number.size() < rev_dot_pos_new) {
        int i = 0;
        while (number.size() + i < rev_dot_pos_new)
          number.push_back(0);
      }
      int dot_pos_new     = number.size() - rev_dot_pos_new;

      int cnt = 0;

      int non_zero_pos = 0;
      while (number[non_zero_pos] == 0 && non_zero_pos < rev_dot_pos_new)
        non_zero_pos++;

      int i = number.size() - 1;
      while (i >= non_zero_pos) {
        if (cnt == dot_pos_new)
          cout << ".";
        else
          cout << number[i--];

        cnt++;
      }


      /*
      for (int i = number.size() - 1; i >= 0; i--)
        cout << number[i];
      */

      cout << endl;
    }
  }

  return 0;
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

  if (res[res.size() - 1] == 0)
    res.pop_back();

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
