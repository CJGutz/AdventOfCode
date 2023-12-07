use std::collections::HashMap;

use crate::common::io::day_input;

pub fn run() {
    let input = day_input(4);

    let mut copies_and_wins_per_card: HashMap<u32, (u32, u32)> = HashMap::new();
    for (index, line) in input.lines().enumerate() {
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
            .map(|n| n.parse::<u32>().unwrap())
            .collect::<Vec<_>>();
        let mut wins = 0;
        for card in own_cards {
            for win in &winning {
                if card == *win {
                    wins += 1
                }
            }
        }
        let h = &mut copies_and_wins_per_card;
        let current_copies = h.get(&(index as u32)).unwrap_or(&(0, 0)).0;
        h.insert(index as u32, (current_copies + 1, wins));
        for copy_index in 1..wins + 1 {
            let i = copy_index + index as u32;
            let previous = h.get(&i).unwrap_or(&(0, 0));
            h.insert(i, (previous.0 + current_copies + 1, previous.1));
        }
    }

    let result: u32 = copies_and_wins_per_card.iter().map(|(_k, v)| v.0).sum();

    println!("{}", result);
}
