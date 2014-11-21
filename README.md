Scout Book
==========

* [Project page](http://wiki.eclipse.org/Scout/Book)
* [Distribution folder (PDF, HTML, EPUB)](http://tools.bsiag.com/scoutbook/)


Building the Book with make
---------------------------

### Initial work:

1. Depending on your local platform copy the file `makefile._platform_` to `makefile`.
1. Adjust your `makefile` according to your local installation.
1. Do not push you `makefile` to the books main repository.
1. Do not include your `makefile` in pull requests you send us.

### Clean

Run:

    >make clean

This will remove the files in the `tex/` folder (for the configured `BOOK`, `FILE` and `HELPBOOK`) and erase the content of `out/`.

### Build a book

Change the name of the book in the makefi line in the `makefile`:

    BOOK=scout_intro

Possible values:

* `scout_intro`
* `scout_frontend`

#### PDF version of a book

To obtain the PDF Version run:

    >make pdf
 
 The result will be copied in `/out/pdf/`.

#### HTML version of a book
 
To obtain the HTML Version run:

    >make html
 
 The result will be copied in `/out/html/`.

#### EPub version of a book

To obtain the HTML Version run:

    >make epub
 
 The result will be copied in `/out/epub/`.

### Build a page
Change the name of the book in the makefi line in the `makefile`:

    FILE=scout_install

Possible values:

* `scout_install`
* `scout_helloworld`

#### PDF version of a page

To obtain the PDF Version run:

    >make file-pdf
 
 The result will be copied in `/out/pdf/`.

#### HTML version of a page
 
To obtain the HTML Version run:

    >make file-html
 
 The result will be copied in `/out/html/`.

### Build the eclipse help (special type of book)

Check the name of the book in the makefi line in the `makefile`:

    HELPBOOK=scout_help

For the moment this is the only possible value.

#### HTML version of the eclipse help
 
To obtain the HTML Version run:

    >make help-html
 
The result will be copied in `/out/html/`.



Issue tracker / Get in touch
----------------------------

* Use the [issue tracker](http://github.com/BSI-Business-Systems-Integration-AG/scoutbook/issues) on GitHub.
* Use the [Eclipse Scout Forum](http://www.eclipse.org/forums/eclipse.scout)



License
-------

This work is licensed under the [Creative Commons Attribution License](http://creativecommons.org/licenses/by/2.0/)
