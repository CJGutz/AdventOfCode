use crate::common::io::day_input;
use phf::phf_map;

static WORDS_TO_NUMBERS: phf::Map<&'static str, u32> = phf_map! {
    "one" => 1,
    "two" => 2,
    "three" => 3,
    "four" => 4,
    "five" => 5,
    "six" => 6,
    "seven" => 7,
    "eight" => 8,
    "nine" => 9,
};

fn sorted_digits(line: &str) -> Vec<(usize, u32)> {
    let line = line.to_string();
    let digit_indexes: Vec<(usize, u32)> = WORDS_TO_NUMBERS
        .entries()
        .map(|(k, v)| (line.match_indices(k).collect::<Vec<_>>(), v))
        .flat_map(|(indecies, v)| indecies.into_iter().map(move |(i, _)| (i, *v)))
        .collect();

    let best_char_index_vec = line
        .chars()
        .into_iter()
        .enumerate()
        .map(|(i, c)| (i, c.to_digit(10)))
        .filter(|(_i, c)| c.is_some())
        .map(|(i, c)| (i, c.unwrap()));

    let chained = &mut best_char_index_vec.chain(digit_indexes).collect::<Vec<_>>();
    chained.sort_by(|(ai, _), (bi, _)| ai.partial_cmp(bi).unwrap());
    chained.to_owned()
}

pub fn run() {
    let input = day_input(1);

    let sum: u32 = input
        .lines()
        .map(|line| {
            let digits = sorted_digits(line);
            let ofirst = digits.first();
            let osecond = digits.last();
            if ofirst.is_none() || osecond.is_none() {
                return 0;
            }
            let (_, first_digit) = ofirst.unwrap();
            let (_, second_digit) = osecond.unwrap();
            return first_digit * 10 + second_digit;
        })
        .sum();
    println!("{}", sum);
}
