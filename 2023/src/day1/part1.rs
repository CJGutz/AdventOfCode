use crate::io::day_input;

pub fn run() {
    let input = day_input(1);
    let sum: u32 = input
        .lines()
        .map(|line| {
            let mut calibration_value = String::new();
            for c in line.chars().into_iter() {
                if c.is_digit(10) {
                    calibration_value.push_str(&c.to_string());
                    break;
                }
            }
            for c in line.chars().rev().into_iter() {
                if c.is_digit(10) {
                    calibration_value.push_str(&c.to_string());
                    break;
                }
            }
            return calibration_value.parse::<u32>();
        })
        .filter_map(|e| e.ok())
        .sum();
    println!("{}", sum);
}
