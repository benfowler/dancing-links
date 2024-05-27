# Fast (if not efficient) depth-first search using Knuth's 'Algorithm X'

## Overview

[dancing-links](http://en.wikipedia.org/wiki/Dancing_Links) is a Java
implementation of Donald Knuth's Dancing Links algorithm, which is a fast
implementation of his [Algorithm X](http://en.wikipedia.org/wiki/Algorithm_X)
algorithm, to solve the exact matrix cover problem.

### Why is this interesting?

Some hard (NP-complete) problems can only be solved by brute force.  Examples
include the [exact cover problem](https://en.wikipedia.org/wiki/Exact_cover),
Sudoku, the n-queens problem, and jigsaw puzzles, amongst others.

Brute-force search is inefficient, but sometimes the _only_ way to solve these
problems.  It turns out that NP-complete problems can be mapped into other
NP-complete problems, and that
[clever algorithms](https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X), like
Donald Knuth's famous 'Algorithm X' exist to do these kinds of brute-force
searches without having to copy lots of state in the process.

### The exact cover problem

These kinds of constraint-satisfaction problems can be expressed as matrices
of ones and zeros, which can then be fed into Algorithm X:

* The columns correspond to constraints in the problem
* The rows correspond to _all configurations_, touching every possible constraint
  in every combination

### Applications

For example, for a puzzle consisting of irregularly-shaped puzzle pieces on an
n-by-n grid, the columns represent "Grid square (x, y) is occupied", and the
rows correspond to every possible position and orientation of each puzzle piece.

The solver returns every possible subset of rows which solve the problem.  In
this case, each row is a puzzle piece in a certain position and orientation.

Another interesting, if less obvious, example would be Sudoku:

* The columns represent the following constraints:
  * Number 'n' in a column
  * Number 'n' in a row
  * Number 'n' in a region
* The rows represent the placement of a number, _n_, in one row, one column, and
  one region.

The input matrix is then set up by generating a row for each 'given', plus all
possible numbers in all remaining cells.

Note that while this can solve Sudokus very quickly (under a millisecond on old
hardware), it does not yield any information on how difficult it might be for a
human to solve.  For that part, a different algorithm, using conventional Sudoku
solving rules, must be used.

### Further reading

See Knuth's [very approachable paper][1] describing the algorithm and some
interesting applications.

## Support

`dancing-links` is supplied as-is.  If it breaks, you get to keep both pieces.

## Included Examples

A few examples are included as subprojects to the main 'dlx' modules.  Unit
tests are also included.  The example applications are most useful for
understanding how to set up constraints and feed them into the solver, how
to run the solver, and decode results.

Here's some of the examples included:

### Sudoku solver

A classic application of the exact matrix cover problem is the efficient
solution of Sudoku; on a very old machine, this solver can brute-force the
solution to the hardest Sudoku problem in less than a millisecond.  A large
collection of very hard Sudoku are included in the test suite.

### n-Queens

Like it says.

### 3D Tetramino

This example was written to crack a 3D tetramino-style block puzzle given to
my parents.  This is a straightforward extrapolation of the tetramino solver
that Knuth describes in his paper.

### Dr Wood's Kaleidoscope Puzzle

I was given this versatile puzzle one Christmas, and instead of grinding through
through his puzzles manually, thought it would be more fun to smash them with
this Kaleidoscope solver instead.

An interesting extension of this example would be to write a graphical editor
to help write new (valid) puzzles and grade their difficulty.

[1]: http://arxiv.org/pdf/cs.DS/0011047.pdf

## Future work

I would like to clean up and modernise the solver's API, with a DSL to generate
primary and secondary columns in a cleaner and more direct way.

The algorithm uses the stack to track what rows and columns are covered, so it
is necessarily single-core.  It might be fun to try to think of ways of how this
constraint might be relaxed.
