### Input:

    1. Length of the List (n)
    2. Number of pages in each chapter (List<Integer> pages)
    3. Number of days available (d)

### Output:

    Find the minimum number of pages the student has to read(x) to complete
    all the chapters with in the given number of days


### Rules for Reading the book:
    
    1. Only one chapter can be read per day. 
    2. If the pages in the chapter <= x then chapter can be completed in one day
    3. If pages in chapter > x then student reads x pages each day until all pages in that chapter are done.


### Example 1:

#### Input:

     5 => n
     2
     3
     4
     5
     5 => pages = [2, 3, 4, 5, 5]
    10 => d
    
#### Output:

    x=2 => 1 + 2 + 2 + 3 + 3 = 11 days
    x=3 => 1 + 1 + 2 + 2 + 2 = 8 days ... 10 days
    x=4 => 1 + 1 + 1 + 2 + 2 = 7 days
    x=5 => 1 + 1 + 1 + 1 + 1 = 5 days

    Ans: 3 pages

    x - started with chapter with minimum page count and incremented


### Example 2:

#### Input:

     5 => n
     5
     5
     5
     5
     5 => pages = [5, 5, 5, 5, 5]
     6 => d

#### Output:

    x=5 => 1 + 1 + 1 + 1 + 1 = 5 days ... 6 days

    Ans: 5 pages


### Example 3:

#### Input:

      5 => n
      2
     10
     15
     55
    150 => pages = [2, 10, 15, 55, 90]
      6 => d

#### Output:

    x=150 => (number of chapters) = 5 days
    x=55 => 4*1 + 3 = 7 days

    Ans: b/w 55 to 150 pages = 150/2 = 75 pages


Logic:

    days = foreach(page -> ciel(page/x)).sum() find days nearer to given d