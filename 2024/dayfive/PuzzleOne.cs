class PuzzleOne
{
    static bool isOrdered(Dictionary<int, List<int>> orderingRules, List<int> pageNumbers)
    {
        List<int> pageNumbersSeen = new List<int>();
        foreach (var number in pageNumbers)
        {
            if (!orderingRules.ContainsKey(number))
            {
                pageNumbersSeen.Add(number);
                continue;
            } 
            var rules = orderingRules[number];
            foreach(var r in rules)
            {
                if (pageNumbersSeen.Contains(r))
                {
                    return false;
                }
            }
            pageNumbersSeen.Add(number);
        }
        return true;
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