use crate::common::io::day_input;

pub fn run() {
    let input = day_input(4);

    let result: u32 = input
        .lines()
        .into_iter()
        .map(|line| {
            let card_split = line.split(":").collect::<Vec<&str>>()[1];
            let winning_own_split = card_split.trim().split("|").collect::<Vec<_>>();
            let winning = winning_own_split[0]
                .trim()
                .split_whitespace()
                .map(|n| n.parse::<u32>().unwrap())
                .collect::<Vec<_>>();
            let own_cards = winning_own_split[1]
                .trim()
                .split_whitespace()
                .map(|n| n.parse::<u32>().unwrap());

            let mut sum = 0;
            let mut doubler = 1;
            for card in own_cards {
                for win in &winning {
                    if card == *win {
                        sum = doubler;
                        doubler *= 2;
                    }
                }
            }
            return sum;
        })
        .sum();
    println!("{}", result);
}
