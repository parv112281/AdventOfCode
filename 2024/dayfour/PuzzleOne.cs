class PuzzleOne
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

    public static int SearchWord(string[] grid, int x, int y, string wordSoFar, string direction = "")
    {
        //Console.WriteLine("SearchWord x=" + x + ", y=" + y + ", wordSoFar=" + wordSoFar + ", direction=" + direction);
        if (wordSoFar == "XMAS") return 1;
        else if (wordSoFar.Count() >= 4) return 0;
        if (direction == "")
        {
            var count = 0;
            foreach(var key in directionDict.Keys)
            {
                var newDir = directionDict[key];
                if (newDir[0] + x < 0 || 
                    newDir[0] + x >= grid.Count() ||
                    newDir[1] + y < 0 ||
                    newDir[1] + y >= grid[newDir[0] + x].Count())
                {
                    // do nothing
                }
                else
                {
                    count += SearchWord(grid, x, y, wordSoFar, key);
                }
            }
            return count;
        }
        else
        {
            var nextDir = directionDict[direction];
            if (nextDir[0] + x < 0 || 
                nextDir[0] + x >= grid.Count() ||
                nextDir[1] + y < 0 ||
                nextDir[1] + y >= grid[nextDir[0] + x].Count())
            {
                return 0;
            }
            else
            {
                wordSoFar += grid[x + nextDir[0]][y + nextDir[1]];
                return SearchWord(grid, x + nextDir[0], y + nextDir[1], wordSoFar, direction);
            }
        }
        throw new Exception("SearchWord: not sure how we eneded up here.");
    }

    public static int Solve(string filename)
    {
        var grid = File.ReadAllLines(filename);
        var count = 0;
        for (int i = 0; i < grid.Count(); ++i) 
        {
            for (int j = 0; j < grid[i].Count(); ++j)
            {
                if (grid[i][j] == 'X')
                {
                    count += SearchWord(grid, i, j, "X");
                }
            }
        }
        return count;
    }
}