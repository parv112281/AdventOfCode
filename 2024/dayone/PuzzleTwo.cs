using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;

class PuzzleTwo
{
    public static Dictionary<long, long> GetNumberCounts(List<long> numbers)
    {
        var numCounts = new Dictionary<long, long>();
        foreach(long num in numbers)
        {
            if (numCounts.ContainsKey(num))
            {
                numCounts[num] += 1;
            }
            else 
            {
                numCounts[num] = 1;
            }
        }
        return numCounts;
    }

    public static List<string> SplitWords(string s)
    {
        return string.IsNullOrEmpty(s) ? new List<string>() : s.Split(new[] { ' ', '\t' }, StringSplitOptions.RemoveEmptyEntries).ToList();
    }

    public static long GetSimilarityScore(List<long> list1, List<long> list2)
    {
        // create dict from list 2
        var dict = GetNumberCounts(list2);
        // iterate through list 1 and caculate similarity score
        long similarityScore = 0l;
        foreach(long num in list1)
        {
            if (dict.ContainsKey(num))
            {
                similarityScore += num * dict[num];
            }
        }
        return similarityScore;
    }

    public static long Solve(string filename)
    {
        var list1 = new List<long>();
        var list2 = new List<long>();
        var inputText = File.ReadAllLines(filename);
        foreach(string line in inputText)
        {
            List<long> numbers = SplitWords(line).Select(long.Parse).ToList();
            list1.Add(numbers[0]);
            list2.Add(numbers[1]);
        }
        return GetSimilarityScore(list1, list2);
    }
}