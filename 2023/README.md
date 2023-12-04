# Advent of Code 

Some solutions for advent of code written in Rust

**Aka**: Programs created when I should have either spent time practicing for my exams or be with my family

## Running
Run a particular day with
```sh 
cargo run {day} {part 1|2}
```
e.g. Run day 4 part 2:
```sh 
cargo run 4 2
```

## Adding a new day
To add a new day you have to
1. create a folder called day{day}/ (e.g. day4/)
2. Add four files: mod.rs, part1.rs, part2.rs, and input.txt
3. Add this code snippet in mod.rs
```rust
mod part1;
mod part2;
use crate::{generate_day, Part};

generate_day!(day{day}, part1, part2);
```
4. Implement run functions inside each part file.
5. Add the day method in main.rs to the DAYS array.

## Access problem

Access problem with
```sh
open https://adventofcode.com/{year}/day/{day}
```
