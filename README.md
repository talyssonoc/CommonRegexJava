CommonRegexJava
=============

[CommonRegex](https://github.com/madisonmay/CommonRegex/ "CommonRegex") port for Java

Find a lot of kinds of common information in a string.

Pull requests welcome!

It supports only US standards (for phones, times and so on) for a while.

API
===

You can instantiate a CommonRegex object passing a CharSequence (String, StringBuilder, StringBuffer...) in the constructor and use the methods without arguments to get the matches, or use the static methods passing the CharSequence as parameter.

Methods:

* `getDates([CharSequence text])`
* `getTimes([CharSequence text])`
* `getPhones([CharSequence text])`
* `getLinks([CharSequence text])`
* `getEmails([CharSequence text])`
* `getIPv4([CharSequence text])`
* `getIPv6([CharSequence text])`
* `getHexColors([CharSequence text])`
* `getAcronyms([CharSequence text])`
* `getMoney([CharSequence text])`
* `getPercentages([CharSequence text])`

Examples
========

    String text = "John, please get that article on www.linkedin.com to me by 5:00PM\n"
                + "on Jan 9th 2012. 4:00 would be ideal, actually. If you have any questions,\n"
                + "you can reach my associate at (012)-345-6789 or associative@mail.com.\n"
                + "I\'ll be in UK during the whole week at a J.R.R. Tolkien convention.";
        
    CommonRegex commonRegex = new CommonRegex(text);
    commonRegex.dates; //["Jan 9th 2012"]
    commonRegex.times; //["5:00PM", "4:00"]
    commonRegex.phones; //["(012)-345-6789"]
    commonRegex.links; //["www.linkedin.com"]
    commonRegex.emails; //["associative@mail.com"]
    ommonRegex.getAcronyms(); //["UK", "J.R.R."]

Alternatively, you can generate a single CommonRegex instance and use it to parse multiple segments of text.

    CommonRegex commonRegex = new CommonRegex();

    commonRegex.times("When are you free? Do you want to meet up for coffee at 4:00?"); //["4:00"]
    commonRegex.getMoney("They said the price was US$5,000.90, actually it is US$3,900.5. It\'s $1100.4 less, can you imagine this?"); //["US$5,000.90", "US$3,900.5", "$1100.4"]
    
For a pratical example, see the tests.

CommonRegex Ports
=================
There are CommonRegex ports for other languages, see [here](https://github.com/madisonmay/CommonRegex/#commonregex-ports "CommonRegex ports")

License
=======
The MIT License (MIT)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.