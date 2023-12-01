use std::fs;

/// Reads the day's input file and returns
/// the contents as a String.
/// Expects the file to be located at src/day{day}/input.txt
pub fn day_input(day: u32) -> String {
    let path = format!("src/day{}/input.txt", day);
    fs::read_to_string(&path)
        .expect(&format!("Failed to read input {}", path))
        .trim()
        .to_string()
}
