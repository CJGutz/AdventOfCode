use std::fs;

pub fn day_input(day: u32) -> String {
    let path = format!("inputs/day{}.txt", day);
    fs::read_to_string(&path)
        .expect(&format!("Failed to read input {}", path))
        .trim()
        .to_string()
}
