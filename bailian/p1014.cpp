#include <iostream>

using namespace std;

void dfs(int value, int level);

int marbles[6] = {0};

int total_value = 0;
int half_value  = 0;

bool flag = false;

int main()
{
  int k = 1;
  while (true)
  {
    int n = 0;
    int i = 0;
    total_value = 0;
    flag = false;
    while (i < 6 && cin >> n)
    {
      marbles[i] = n;
      total_value += (i + 1) * marbles[i];

      i++;
    }

    if (total_value == 0) break;

    cout << "Collection #" << k << ":" << endl;
    if (total_value % 2)
    {
      cout << "Can't be divided." << endl;
    }

    half_value = total_value / 2;

    dfs(0, 5);


    if (flag)
      cout << "Can be divided." << endl;
    else
      cout << "Can't be divided." << endl;
    
  }
}

void dfs(int value, int level)
{

  if (flag == true)
    return;

  if (value == half_value)
  {
    flag = true;
    return;
  }

  if (value > half_value)
  {
    return;
  }

  if (level < 0)
    return;

  if (marbles[level] > 0)
  {
    if (value + level + 1 <= half_value)
    {
      marbles[level]--;
      dfs(value + level + 1, level);
      marbles[level]++;

      if (flag == true)
        return;
    }
  }

  dfs(value, level - 1);
}
