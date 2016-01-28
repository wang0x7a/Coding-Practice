#include <iostream>

using namespace std;

void dfs(int value, int level);

int marbles[7] = {0};

int total_value = 0;
int half_value  = 0;

bool flag = false;

int main()
{
  int k = 1;
  while (true)
  {
    int n = 0;
    int i = 1;
    total_value = 0;
    flag = false;
    while (i <= 6 && cin >> n)
    {
      marbles[i] = n;
      total_value += i * marbles[i];

      i++;
    }

    if (total_value == 0) break;

    cout << "Collection #" << k++ << ":" << endl;
    if (total_value % 2)
    {
      cout << "Can't be divided.\n" << endl;
      continue;
    }

    half_value = total_value / 2;

    dfs(0, 6);


    if (flag)
      cout << "Can be divided.\n" << endl;
    else
      cout << "Can't be divided.\n" << endl;

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

  for (int i = level; i > 0; i--)
  {
    if (marbles[i] > 0)
    {
      if (value + i <= half_value)
      {
        marbles[i]--;
        dfs(value + i, i);
        //marbles[i]++;

        if (flag == true)
          return;
      }
    }
  }

}
