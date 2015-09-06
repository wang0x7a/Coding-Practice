#include <iostream>
#include <string>
#include <cstring>
#include <cstdio>
#include <cmath>

#define MAX_COIN_NUM 12

using namespace std;

int coins[MAX_COIN_NUM];
int zero[MAX_COIN_NUM];

void set_record(string side, int value)
{
  for (int i = 0, idx = 0; i < 4; i++)
  {
    idx = side[i] - 'A';
    
    if (zero[idx])
    {
      coins[idx] += value;
    }
  }
}

int main()
{
  int case_num = 0;

  cin >> case_num;

  for (int i = 0; i < case_num; i++)
  {
    memset(coins, 0, sizeof(int) * MAX_COIN_NUM);
    memset(zero, 1, sizeof(int) * MAX_COIN_NUM);

    for (int weighing_num = 0; weighing_num < 3; weighing_num++)
    {
      string left, right, result;

      cin >> left >> right >> result;

      int idx;
      if (result == "even")
      {
        for (int i = 0; i < 4; i++)
        {
          idx = left[i] - 'A';
          zero[idx] = 0;
          coins[idx] = 0;
        }

        for (int i = 0; i < 4; i++)
        {
          idx = right[i] - 'A';
          zero[idx] = 0;
          coins[idx] = 0;
        }
      }
      else if (result == "up")
      {
        set_record(left, 1);
        set_record(right, -1);
      }
      else
      {
        set_record(left, -1);
        set_record(right, 1);
      }
    }

    int fake = 0;
    int max_time = 0;
    for (int j = 0; j < MAX_COIN_NUM; j++)
    {
      if (abs(coins[j]) > max_time)
      {
        max_time = abs(coins[j]);
        fake = j;
      }

    }

    if (coins[fake] > 0)
    {
      printf("%c is the counterfeit coin and it is heavy.\n",
              (char)(fake + 'A'));
    }
    else if (coins[fake] < 0)
    {
      printf("%c is the counterfeit coin and it is light.\n", 
              (char)(fake + 'A'));
    }

  }
}
