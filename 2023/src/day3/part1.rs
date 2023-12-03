use crate::io::day_input;

struct Symbol {
    row: u32,
    column: u32,
}

struct Number {
    row: u32,
    column: u32,
    number: u32,
}

fn create_number(col: u32, row: u32, number_string: &str) -> Number {
    Number {
        column: col - number_string.len() as u32,
        row,
        number: number_string
            .parse()
            .expect(&format!("Could not parse {} to a number", number_string)),
    }
}

fn retrieve_symbols_and_numbers(line: &str, line_num: u32) -> (Vec<Symbol>, Vec<Number>) {
    let mut symbols = vec![];
    let mut numbers = vec![];
    let mut current_num = String::new();
    for (index, character) in line.chars().enumerate() {
        if !character.is_digit(10) {
            if current_num.len() > 0 {
                numbers.push(create_number(index as u32, line_num, &current_num));
                current_num = String::new();
            }
            if character != '.' {
                symbols.push(Symbol {
                    row: line_num,
                    column: index as u32,
                })
            }
        } else {
            current_num.push_str(&character.to_string())
        }
    }
    if current_num.len() > 0 {
        numbers.push(create_number(
            (line.len() - 1) as u32,
            line_num,
            &current_num,
        ));
    }
    return (symbols, numbers);
}

fn near_num(number: &Number, symbol: &Symbol) -> bool {
    if number.row.abs_diff(symbol.row) > 1 {
        return false;
    }
    if symbol.column + 1 >= number.column
        && number.column + number.number.to_string().len() as u32 >= symbol.column
    {
        return true;
    }
    return false;
}

pub fn run() {
    let input = day_input(3);
    let mut symbols = vec![];
    let mut numbers = vec![];
    input
        .lines()
        .enumerate()
        .map(|(index, line)| retrieve_symbols_and_numbers(line, index as u32))
        .for_each(|(sym, num)| {
            symbols.extend(sym);
            numbers.extend(num);
        });
    let mut sum = 0;
    for num in numbers {
        for sym in &symbols {
            if near_num(&num, sym) {
                sum += num.number;
            }
            if sym.row > num.row + 1 {
                break;
            }
        }
    }
    println!("Sum: {}", sum);
}
