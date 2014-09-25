#include <iostream>

using namespace std;

string HAAB_MONTH[] = {"pop", "no", "zip", "zotz", "tzec", "xul", "yoxkin",
  "mol", "chen", "yax", "zac", "ceh", "mac", "kankin", "muan", "pax", "koyab",
  "cumhu", "uayet"};

string TZOLKIN_MONTH[] = {"imix", "ik", "akbal", "kan", "chicchan", "cimi",
  "manik", "lamat", "muluk", "ok", "chuen", "eb", "ben", "ix", "mem", "cib",
  "caban", "eznab", "canac", "ahau"};

int stoi_(string s);

int main() {
  int num;
  string day_str, month;
  int day, year;

  cin >> num;
  cout << num << endl;

  for (int i = 0; i < num; i++) {
    cin >> day_str >> month >> year;

    int num_of_days = 0;
    day = stoi_(day_str);

    int j = 0;
    for (; j < 19; j++)
      if (HAAB_MONTH[j] == month)
        break;

    num_of_days = year * 365 + j * 20 + day;

    cout << (num_of_days % 13 + 1) << " " << TZOLKIN_MONTH[num_of_days % 20] 
      << " " << num_of_days / 260 << endl;
  }
}

int stoi_(string s) {
  int res = 0;

  for (int i = 0; i < s.size() - 1; i++)
    res = res * 10 + s[i] - '0';

  return res;
}
