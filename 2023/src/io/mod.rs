use std::fs;

pub fn day_input(day: u32) -> String {
    let path = format!("inputs/day{}.txt", day);
    fs::read_to_string(path)
        .expect("Failed to read input")
        .trim()
        .to_string()
}
