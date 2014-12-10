#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_STAMP_NUM 4
#define MAX_TYPE_NUM  25
#define MAX_SELL_NUM  390625

typedef struct {
  int stamp_num;
  int highest_value;
  int type_num;

  int stamps[MAX_STAMP_NUM];
} Record;

int legal_sell_num = 0;
Record legal_sells[MAX_SELL_NUM];

int type_num = 0;
int types[MAX_TYPE_NUM];

void solve(int req);
void dfs(int req, int last_idx, int idx, Record curr);
void print_record(Record record);
bool cmp_record(Record a, Record b);

int main() {
  int n;
  int line_idx = 0;

  while (cin >> n) {
    if (n == 0) {
      if (line_idx % 2) {
        legal_sell_num = 0;
        type_num       = 0;
      }

      line_idx++;
      continue;
    }

    if (line_idx % 2) {
      sort(types, types + type_num);
      solve(n);
      legal_sell_num = 0;
    }
    else {
      types[type_num] = n;
      type_num++;
    }
  }
}

bool cmp_record(Record a, Record b) {
  if (a.type_num == b.type_num) {
    if (a.stamp_num == b.stamp_num)
      return a.highest_value > b.highest_value;
    else
      return a.stamp_num < b.stamp_num;
  }
  else
    return a.type_num > b.type_num;
}

void print_record(Record record) {
  cout << " (" << record.type_num << "):";

  for (int i = 0; i < record.stamp_num; i++)
    cout << " " << record.stamps[i];
}

void solve(int req) {
  Record acc;
  acc.stamp_num     = 0;
  acc.type_num      = 0;
  acc.highest_value = 0;

  dfs(req, -1, 0, acc);

  cout << req;
  if (legal_sell_num == 0)
    cout << " ---- none";
  else if (legal_sell_num == 1)
    print_record(legal_sells[0]);
  else {
    sort(legal_sells, legal_sells + legal_sell_num, cmp_record);

    if (legal_sells[0].stamp_num < legal_sells[1].stamp_num 
        || legal_sells[0].highest_value > legal_sells[1].highest_value
        || legal_sells[0].type_num > legal_sells[1].type_num)
      print_record(legal_sells[0]);
    else
      cout << " (" << legal_sells[0].type_num << "): tie";
  }

  cout << endl;
}

void dfs(int req, int last_idx, int idx, Record acc) {
  if (req == 0 && acc.stamp_num <= MAX_STAMP_NUM) {
    legal_sells[legal_sell_num] = acc;
    legal_sell_num++;
    return;
  }

  if (idx >= type_num || types[idx] > req || acc.stamp_num > MAX_STAMP_NUM)
    return;

  Record tmp = acc;

  // select current denomination
  acc.stamps[acc.stamp_num] = types[idx];
  acc.stamp_num++;
  acc.highest_value = types[idx];

  if (last_idx != idx)
    acc.type_num++;

  dfs(req - types[idx], idx, idx, acc);

  // do not selelct current denomination
  dfs(req, idx, idx + 1, tmp);

  return;
}
