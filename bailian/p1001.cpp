#include <iostream>

using namespace std;

string calc(string base, int expnt);
string multiply2(string a, string b);
int get_dot_pos(string a);
string tail_zeros(string a);
string postproc(string a);

int main() {
  //cout << calc("9", 3) << endl;
  string base;
  int expnt;

  string res;
  while (cin >> base >> expnt) {
    int dot_pos = get_dot_pos(base);
    
    string int_part = base.substr(0, dot_pos);
    string dec_part = base.substr(dot_pos + 1, base.size() - dot_pos);

    string new_base = int_part + dec_part;

    res = calc(new_base, expnt);

    cout << res << endl;

    /*
    string int_res  = calc(int_part, expnt);
    string dec_res  = calc(dec_part, expnt);

    dec_res = tail_zeros(dec_res);

    cout << int_res + "." + dec_res << endl;
    */
  }
}

string tail_zeros(string a) {
  int len = a.size();

  int i;
  for (i = len - 1; i >= 0; i++)
    if (a[i] != '0')
      break;

  return a.substr(0, i + 1);
}

int get_dot_pos(string a) {
  for (int i = 0; i < a.size(); i++)
    if (a[i] == '.')
      return i;

  return -1;
}

string calc(string base, int expnt) {
  if (expnt == 1 || base.compare("0") == 0)
    return base;

  string tmp = expnt % 2 == 0 ? "1" : base;

  string half = calc(base, expnt / 2);

  //string res = multiply2(half, half);
  //res = multiply2(res, tmp);

  // set the row length as the value of the shorter input
  //return multiply2(multiply2(half, half), tmp);
  return multiply2(tmp, multiply2(half, half));
}

string multiply2(string a, string b) {
  string res;
  int len_a = a.size();
  int len_b = b.size();

  int row = len_a;
  int col = len_a + len_b - 1;

  int **m;
  m = new int*[row];
  // init each row and compute the value of each cell
  for (int i = 0; i < row; i++) {
    m[i] = new int[col];

    for (int j = 0; j < len_b; j++) {
      m[i][i + j] = (a[i] - '0') * (b[j] - '0');
    }
  }

  /*
  for (int i = 0; i < row; i++) {
    for (int j = 0; j < col; j++)
      cout << m[i][j];

    cout << endl;
  }
  */

  int carry = 0;
  char *str_arr = new char[col];
  int tmp = 0;
  for (int j = col - 1; j >= 0; j--) {
    tmp += carry;
    for (int i = 0; i < row; i++)
      tmp += m[i][j];

    carry = tmp / 10;
    str_arr[j] = tmp % 10 + '0';
    tmp = 0;
  }

  //cout << str_arr << endl;

  if (carry == 0)
    res = string(str_arr);
  else
    res = to_string(carry) + string(str_arr);

  delete m;
  delete str_arr;

  return res;
}
