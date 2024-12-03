using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;

class PuzzleOne
{
    public static List<string> SplitWords(string s)
    {
        return string.IsNullOrEmpty(s) ? new List<string>() : s.Split(new[] { ' ', '\t' }, StringSplitOptions.RemoveEmptyEntries).ToList();
    }

    public static bool IsSafeList(List<long> numbers)
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