/// Generates a day module with the given day identifier and two parts.
/// The two modules must contain functions called `run` that take no arguments.
///
/// Example usage:
/// ```
/// day_gen!(day1, part1, part2);
/// ```
/// This will run the `run` function in `part1.rs` when given Part::One
#[macro_export]
macro_rules! generate_day {
    ($day:ident, $module1:ident, $module2:ident) => {
        pub fn $day(part: Part) {
            match part {
                Part::One => $module1::run(),
                Part::Two => $module2::run(),
            }
        }
    };
}
