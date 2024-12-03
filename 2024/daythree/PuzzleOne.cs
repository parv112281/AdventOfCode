using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;
using System.Text.RegularExpressions;

class PuzzleOne
{
    private static List<string> SplitWords(string s)
    {
        return string.IsNullOrEmpty(s) ? new List<string>() : s.Split(new[] { ' ', '\t' }, StringSplitOptions.RemoveEmptyEntries).ToList();
    }

    private static List<string> ExtractMulStrings(string s)
    {
        string pattern = @"mul\(\d+,\d+\)";
        var matches = Regex.Matches(s, pattern);
        return matches.Cast<Match>().Select(m => m.Value).ToList(); 
    }

    private static long ExecuteMul(string line)
    {
        string pattern = @"\d+";
        var matches = Regex.Matches(line, pattern);
        var numbers = matches.Cast<Match>().Select(m => long.Parse(m.Value)).ToList(); 
        if (numbers.Count == 2)
        {
            return numbers[0] * numbers[1];
        }
        else 
        {
            Console.WriteLine("Error, incorrect count for numbers list: " + string.Join(", ", numbers));
            throw new Exception("Error, incorrect count for numbers list");
        }
    }

    public static long Solve(string filename)
    {
        var inputText = File.ReadAllLines(filename);
        long sum = 0;
        foreach(string line in inputText)
        {
            var muls = ExtractMulStrings(line);
            foreach(string mul in muls)
            {
                sum += ExecuteMul(mul);
            }
        }
        return sum;
    }
}