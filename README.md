Overview
========

[dancing-links](http://en.wikipedia.org/wiki/Dancing_Links) is a Java 
implementation of Donald Knuth's Dancing Links algorithm, which is a fast 
implementation of his [Algorithm X](http://en.wikipedia.org/wiki/Algorithm_X)
algorithm, to solve the exact matrix cover problem.  

See Knuth's [very approachable paper][1] describing the algorithm and some
interesting applications.


Support
=======

dancing-links is supplied as-is.  If it breaks, you get to keep both pieces.


Example Usage
=============

A few examples are included as subprojects to the main 'dlx' modules.  Unit
tests are also included.  The example applications are most useful for 
understanding how to set up constraints and feed them into the solver, how
to run the solver, and decode results.

Here's some of the examples included:

Sudoku solver
-------------

A classic application of the exact matrix cover problem is the efficient 
solution of Sudoku; on a very old machine, this solver can brute-force the
solution to the hardest solver in less than a millisecond.  A large battery
of very hard Sudoku are included in the test suite.

n-Queens
--------

Like it says.

3D Tetramino
------------

This example was written to crack a 3D tetramino-style block puzzle given to
my parents.  This is a straightforward extrapolation of the tetramino solver
that Knuth describes in his paper.

Dr Wood's Kaleidoscope Puzzle
-----------------------------

I was given this cheesy, but surprisingly versatile little puzzle one 
Christmas, and instead of cranking through his puzzles manually, decided to
smash them with this Kaleidoscope solver instead.

An interesting extension of this example, would be to write a graphical editor
to help write new (valid) puzzles and grade their difficulty.


[1]: http://arxiv.org/pdf/cs.DS/0011047.pdf

