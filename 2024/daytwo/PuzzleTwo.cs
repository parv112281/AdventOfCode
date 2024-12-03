using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;

class PuzzleTwo
{
    public static List<string> SplitWords(string s)
    {
        return string.IsNullOrEmpty(s) ? new List<string>() : s.Split(new[] { ' ', '\t' }, StringSplitOptions.RemoveEmptyEntries).ToList();
    }

    private static List<long> RemoveAt(List<long> numbers, int index)
    {
        var newList = new List<long>(numbers);
        newList.RemoveAt(index);
        return newList;
    }

    public static bool IsSafeList(List<long> numbers, int recursionDepth = 0)
    {
        int direction = 0;
        if (numbers.Count < 2)
        {
            return true;
        }
        for(int i = 1; i < numbers.Count; ++i)
        {
            int current_direction = 0;
            if (numbers[i] - numbers[i-1] < 0)
            {
                current_direction = -1;
            }
            else if (numbers[i] - numbers[i-1] > 0)
            {
                current_direction = 1;
            }
            else
            {
                if (recursionDepth == 0)
                {
                    for (int j = 0; j <= i; ++j)
                    {
                        if (IsSafeList(RemoveAt(numbers, j), recursionDepth + 1))
                        {
                            return true;
                        }
                    }
                }
                return false;
            }
            if (direction == 0) {
                direction = current_direction;
            }
            if (
                current_direction != direction || 
                Math.Abs(numbers[i] - numbers[i-1]) > 3
            )
            {
                if (recursionDepth == 0)
                {
                    for (int j = 0; j <= i; ++j)
                    {
                        if (IsSafeList(RemoveAt(numbers, j), recursionDepth + 1))
                        {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    public static long Solve(string filename)
    {
        var inputText = File.ReadAllLines(filename);
        long safeCount = 0L;
        // for each line in the input file
        foreach(string line in inputText)
        {
            // parse line into list of numbers
            List<long> numbers = SplitWords(line).Select(long.Parse).ToList();
            // determine if its a safe list
            if (IsSafeList(numbers)) 
            {
                safeCount++;
            }
        }
        return safeCount;
    }
}