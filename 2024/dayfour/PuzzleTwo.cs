class PuzzleTwo
{
    static Dictionary<string, int[]> directionDict = new Dictionary<string, int[]>
    {
        {"N", new int[] {-1, 0}},
        {"NE", new int[] {-1, 1}},
        {"E", new int[] {0, 1}},
        {"SE", new int[] {1, 1}},
        {"S", new int[] {1, 0}},
        {"SW", new int[] {1, -1}},
        {"W", new int[] {0, -1}},
        {"NW", new int[] {-1, -1}}
    };

    public static bool IsDirectionOutOfBound(string[] grid, int x, int y, string direction)
    {
        var nextDir = directionDict[direction];
        if (nextDir[0] + x < 0 || 
            nextDir[0] + x >= grid.Count() ||
            nextDir[1] + y < 0 ||
            nextDir[1] + y >= grid[nextDir[0] + x].Count())
        {
            return true;
        }
        return false;
    }

    public static char GetLetterFromDirection(string[] grid, int x, int y, string direction)
    {
        var nextDir = directionDict[direction];
        var nextChar = grid[nextDir[0] + x][nextDir[1] + y];
        return nextChar;
    }

    public static bool SearchWord(string[] grid, int x, int y)
    {
        if (!IsDirectionOutOfBound(grid, x, y, "NW") &&
            !IsDirectionOutOfBound(grid, x, y, "NE") &&
            !IsDirectionOutOfBound(grid, x, y, "SW") &&
            !IsDirectionOutOfBound(grid, x, y, "SE"))
        {
            char nwLetter = GetLetterFromDirection(grid, x, y, "NW");
            char neLetter = GetLetterFromDirection(grid, x, y, "NE");
            char swLetter = GetLetterFromDirection(grid, x, y, "SW");
            char seLetter = GetLetterFromDirection(grid, x, y, "SE");
            if (nwLetter != 'M' && nwLetter != 'S') return false;
            if (seLetter != 'M' && seLetter != 'S') return false;
            if (neLetter != 'M' && neLetter != 'S') return false;
            if (swLetter != 'M' && swLetter != 'S') return false;

            if (nwLetter == 'M' && seLetter != 'S') return false;
            if (nwLetter == 'S' && seLetter != 'M') return false;
            if (neLetter == 'S' && swLetter != 'M') return false;
            if (neLetter == 'M' && swLetter != 'S') return false;
            return true;
        }
        return false;
    }

    public static int Solve(string filename)
    {
        var grid = File.ReadAllLines(filename);
        var count = 0;
        for (int i = 0; i < grid.Count(); ++i) 
        {
            for (int j = 0; j < grid[i].Count(); ++j)
            {
                if (grid[i][j] == 'A')
                {
                    if (SearchWord(grid, i, j))
                    {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}