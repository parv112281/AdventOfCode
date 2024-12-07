class PuzzleTwo
{
    static bool isOrdered(Dictionary<int, List<int>> orderingRules, List<int> pageNumbers)
    {
        var reordered = false;
        var isOrdered = false;
        while(!isOrdered) {
            isOrdered = true;
            for (int i = 0; i < pageNumbers.Count - 1; ++i) {
                if (orderingRules.ContainsKey(pageNumbers[i+1])) {
                    var rules = orderingRules[pageNumbers[i+1]];
                    if (rules.Contains(pageNumbers[i])) {
                        var temp = pageNumbers[i];
                        pageNumbers[i] = pageNumbers[i+1];
                        pageNumbers[i+1] = temp;
                        isOrdered = false;
                        reordered = true;
                        break;
                    }
                } else {
                }
            }
        }
        return reordered;
    }

    public static int Solve(string filename)
    {
        var lines = File.ReadAllLines(filename);
        var isBuildingOrderingRules = true;
        var orderingRules = new Dictionary<int, List<int>>();
        var pageNumbersList = new List<List<int>>();
        foreach(var line in lines)
        {
            if (line == "")
            {
                isBuildingOrderingRules = false;
                continue;
            }
            if (isBuildingOrderingRules)
            {
                var rule = line.Split("|").Select(int.Parse).ToList();
                if (!orderingRules.ContainsKey(rule[0]))
                {
                    orderingRules[rule[0]] = new List<int>();
                }
                orderingRules[rule[0]].Add(rule[1]);
            }
            else
            {
                var pageNumbers = line.Split(",").Select(int.Parse).ToList();
                pageNumbersList.Add(pageNumbers);
            }
        }
        var middleSum = 0;
        foreach(var pageNumbers in pageNumbersList)
        {
            if (isOrdered(orderingRules, pageNumbers))
            {
                int midIdx = pageNumbers.Count / 2;
                middleSum += pageNumbers[midIdx];
            }
        }
        return middleSum;
    }
}