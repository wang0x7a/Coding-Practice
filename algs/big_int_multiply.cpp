#include <iostream>
#include <vector>
#include <string>

using namespace std;

vector<int> multiply2(const vector<int>& a, const vector<int>& b);
vector<int> str2vec(const string& s);
void print(const vector<int>& v);

int main() {
  string a, b;

  cin >> a >> b;

  vector<int> vecA = str2vec(a);
  vector<int> vecB = str2vec(b);

  print(vecA);
  print(vecB);

  vector<int> res  = multiply2(vecA, vecB);

  int n = res.size();
  if (res[n - 1] == 0)
    n--;

  for (int i = n - 1; i >= 0; i--)
    cout << res[i];

  cout << endl;
}

void print(const vector<int>& v) {
  int n = v.size();

  for (int i = n - 1; i >=0; i--)
    cout << v[i];

  cout << endl;
}

vector<int> str2vec(const string& s) {
  vector<int> res;

  int len = s.size();

  for (int i = len - 1; i >= 0; i--)
    res.push_back(s[i] - '0');

  return res;
}

vector<int> multiply2(const vector<int>& a, const vector<int>& b) {
  int m = a.size();
  int n = b.size();

  vector<int> res(m + n);

  for (int i = 0; i < m; i++)
    for (int j = 0; j < n; j++)
      res[i + j] += a[i] * b[j];

  int carry = 0;
  for (int i = 0; i < m + n; i++) {
    int tmp = res[i];
    res[i] = (tmp + carry) % 10;
    carry  = (tmp + carry) / 10;
  }

  return res;
}
