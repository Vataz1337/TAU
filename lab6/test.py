import pytest
from game import GameBoard,  Game


def test_is_valid_position():
    board = GameBoard(10, 10)
    assert board.is_valid_position(0, 0) == True
    assert board.is_valid_position(9, 9) == True
    assert board.is_valid_position(10, 10) == False
    assert board.is_valid_position(-1, 5) == False
    assert board.is_valid_position(5, -1) == False

def test_is_empty():
    board = GameBoard(10, 10)
    board.board = [
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    ]
    assert board.is_empty(0, 0) == True
    assert board.is_empty(5, 5) == True
    assert board.is_empty(9, 9) == True
    assert board.is_empty(3, 3) == True
    assert board.is_empty(5, 3) == False
    assert board.is_empty(7, 7) == False

def test_move():
    game = Game(10, 10)
    game.board.board = [
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    ]
    game.board.start_pos = (0, 0)
    game.current_pos = (0, 0)

    game.move(1, 0)
    assert game.current_pos == (1, 0)
    assert game.path == [(0, 0), (1, 0)]

    game.move(0, 1)
    assert game.current_pos == (1, 1)
    assert game.path == [(0, 0), (1, 0), (1, 1)]

    game.move(-1, 0)
    assert game.current_pos == (0, 1)
    assert game.path == [(0, 0), (1, 0), (1, 1), (0, 1)]

    game.move(0, -1)
    assert game.current_pos == (0, 0)
    assert game.path == [(0, 0), (1, 0), (1, 1), (0, 1), (0, 0)]

    game.move(1, 1)
    assert game.current_pos == (1, 1)
    assert game.path == [(0, 0), (1, 0), (1, 1), (0, 1), (0, 0), (1, 1)]


def test_end_game():
    game = Game(10, 10)
    with pytest.raises(SystemExit):
        game.end_game()



