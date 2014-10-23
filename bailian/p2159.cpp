#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int code[26];
int text[26];

void char_stat(string txt, int a[]) {
  int len = txt.size();

  int k;
  for (int i = 0; i < len; i++) {
    k = txt[i] - 'A';
    a[k] += 1; 
  }
}

bool cmp(int a[], int b[], int n) {
  for (int i = 0; i < n; i++) {
    if (a[i] != b[i])
      return false;
  }

  return true;
}

void print(int a[], int n) {
  for (int i = 0; i < n; i++)
    cout << a[i] << " ";

  cout << endl;
}

int main() {
  string str_code, str_text;

  cin >> str_code >> str_text;

  char_stat(str_code, code);
  char_stat(str_text, text);

  sort(code, code + 26);
  sort(text, text + 26);

  //print(code, 26);
  //print(text, 26);

  if (cmp(code, text, 26))
    cout << "YES" << endl;
  else
    cout << "NO" << endl;

}
