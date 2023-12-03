mod day1;
mod day2;
mod day3;
mod generate_day;
mod io;
use clap::{Parser, ValueEnum};
use day1::day1;
use day2::day2;
use day3::day3;

#[derive(ValueEnum, Clone, Debug)]
pub enum Part {
    #[clap(alias = "1")]
    One,
    #[clap(alias = "2")]
    Two,
}

#[derive(Parser)]
struct Args {
    day: u32,

    #[clap(value_enum)]
    part: Part,
}

const DAYS: [fn(Part); 3] = [day1, day2, day3];

fn main() {
    let args: Args = Args::parse();

    let day = args.day;
    let part = args.part;
    if day > DAYS.len() as u32 {
        panic!("Day {} not implemented", day);
    }
    println!("Running day {} part {:?}", day, part);
    DAYS[day as usize - 1](part);
}
