#include <iostream>
#include <algorithm>

using namespace std;

int translate(string s);

int main() {
  int tel_num;

  cin >> tel_num;

  int tel[tel_num];

  string s;
  for (int i = 0; i < tel_num; i++) {
    cin >> s;

    tel[i] = translate(s);
  }

  if (tel_num == 1) {
    cout << "No duplicates." << endl;
    return 0;
  }


  sort(tel, tel + tel_num);

  bool flag = true;
  int prev = tel[0];
  int curr;
  int cnt  = 1;
  for (int i = 1; i < tel_num; i++) {
    curr = tel[i];

    if (curr == prev)
      cnt++;
    else {
      if (cnt > 1) {
        cout << prev / 10000 << "-" << prev % 10000 << " " << cnt << endl;
        flag = false;
      }

      cnt = 1;
      prev = curr;
    }
  }

  if (cnt > 1) {
    cout << curr / 10000 << "-" << curr % 10000 << " " << cnt << endl;
    flag = false;
  }

  if (flag)
    cout << "No duplicates." << endl;
}

int translate(string s) {
  int res = 0;
  int str_len = s.size();

  for (int i = 0; i < str_len; i++) {
    if (s[i] >= '0' && s[i] <= '9')
      res = res * 10 + s[i] - '0';
    else if ((s[i] >= 'A' && s[i] <= 'P') || (s[i] >= 'a' && s[i] <= 'p')) {
      s[i] = toupper(s[i]);
      res = res * 10 + (s[i] - 'A') / 3 + 2;
    }
    else if ((s[i] >= 'R' && s[i] <= 'Y') || (s[i] >= 'r' && s[i] <= 'y')) {
      s[i] = toupper(s[i]);
      res = res * 10 + (s[i] - 'A' - 1) / 3 + 2;
    }
    else
      continue;
  }

  return res;
}
