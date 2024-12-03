using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;

class PuzzleOne
{
    public static List<int> SortList(List<int> unsortedList)
    {
        // TODO implement merge sort or quick sort instead 
        var retList = new List<int>();
        while (unsortedList.Count > 0) {
            int lowestIdx = 0;
            for (int i = 0; i < unsortedList.Count; ++i) {
                if (unsortedList[i] < unsortedList[lowestIdx]) {
                    lowestIdx = i;
                }
            }
            retList.Add(unsortedList[lowestIdx]);
            unsortedList.RemoveAt(lowestIdx);
        }
        return retList;
    }

    public static List<string> SplitWords(string s)
    {
        return string.IsNullOrEmpty(s) ? new List<string>() : s.Split(new[] { ' ', '\t' }, StringSplitOptions.RemoveEmptyEntries).ToList();
    }

    public static int GetDistance(string filename)
    {
        var list1 = new List<int>();
        var list2 = new List<int>();
        var inputText = File.ReadAllLines(filename);
        foreach(string line in inputText)
        {
            List<int> numbers = SplitWords(line).Select(int.Parse).ToList();
            list1.Add(numbers[0]);
            list2.Add(numbers[1]);
        }
        var sortedList1 = SortList(list1);
        var sortedList2 = SortList(list2);
        var distance = 0;
        for (int i = 0; i < sortedList1.Count; i++) 
        {
            distance += Math.Abs(sortedList1[i] - sortedList2[i]);
        }
        return distance;
    }
}