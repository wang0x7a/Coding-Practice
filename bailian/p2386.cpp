#include <iostream>

#define MAX_ROW 100
#define MAX_COL 100

char lake[MAX_ROW][MAX_COL];

using namespace std;

void dfs(int x, int y, int n, int m)
{
  lake[x][y] = '.';
  for (int i = x - 1; i <= x + 1; i++)
  {
    for (int j = y -1; j <= y + 1; j++)
    {
      if (i >= 0 && i <n && j >=0 && j < m && lake[i][j] == 'W')
        //lake[i][j] = '.';
        dfs(i, j, n, m);
    }
  }

  return;

}

int main()
{
  int n, m;
  cin >> n >> m;

  int res = 0;

  for (int i = 0; i < n; i++)
    for (int j = 0; j < m; j++)
      cin >> lake[i][j];

  for (int i = 0; i < n; i++)
  {
    for (int j = 0; j < m; j++)
    {
      if (lake[i][j] == 'W')
      {
        dfs(i, j, n, m);
        res++;
      }
    }
  }

  cout << res << endl;

}
