import pygame
import random

WIDTH = 10
HEIGHT = 10


EMPTY = 0
START = 1
STOP = 2
OBSTACLE = 3
PATH = 4

class GameBoard:
    def __init__(self, width, height):
        self.width = width
        self.height = height
        self.board = [[EMPTY for _ in range(width)] for _ in range(height)]
        self.start_pos = None
        self.stop_pos = None

    def generate_board(self):
        self.clear_board()

        obstacles = random.randint(5, 10)
        for _ in range(obstacles):
            x = random.randint(0, self.width - 1)
            y = random.randint(0, self.height - 1)
            self.board[y][x] = OBSTACLE

        self.start_pos = self.get_random_position()
        self.stop_pos = self.get_random_position(exclude=self.start_pos)

        self.board[self.start_pos[1]][self.start_pos[0]] = START
        self.board[self.stop_pos[1]][self.stop_pos[0]] = STOP

    def clear_board(self):
        self.board = [[EMPTY for _ in range(self.width)] for _ in range(self.height)]
        self.start_pos = None
        self.stop_pos = None

    def get_random_position(self, exclude=None):
        position = (random.randint(0, self.width - 1), random.randint(0, self.height - 1))
        while exclude and position == exclude:
            position = (random.randint(0, self.width - 1), random.randint(0, self.height - 1))
        return position

    def is_valid_position(self, x, y):
        return 0 <= x < self.width and 0 <= y < self.height

    def is_empty(self, x, y):
        return self.is_valid_position(x, y) and self.board[y][x] == EMPTY

    def is_start(self, x, y):
        return self.is_valid_position(x, y) and self.board[y][x] == START

    def is_stop(self, x, y):
        return self.is_valid_position(x, y) and self.board[y][x] == STOP

    def is_obstacle(self, x, y):
        return self.is_valid_position(x, y) and self.board[y][x] == OBSTACLE

    def set_path(self, x, y):
        self.board[y][x] = PATH

    def get_board(self):
        return self.board

class Game:
    def __init__(self, width, height):
        self.board = GameBoard(width, height)
        self.current_pos = None
        self.path = []

    def start_game(self):
        pygame.init()
        self.board.generate_board()
        self.current_pos = self.board.start_pos
        self.path = [self.current_pos]
        self.display_board()

        running = True
        while running:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    running = False
                elif event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_UP:
                        self.move(0, -1)
                    elif event.key == pygame.K_DOWN:
                        self.move(0, 1)
                    elif event.key == pygame.K_LEFT:
                        self.move(-1, 0)
                    elif event.key == pygame.K_RIGHT:
                        self.move(1, 0)

    def move(self, dx, dy):
        new_x = self.current_pos[0] + dx
        new_y = self.current_pos[1] + dy

        if self.board.is_valid_position(new_x, new_y):
            if self.board.is_empty(new_x, new_y):
                self.current_pos = (new_x, new_y)
                self.path.append(self.current_pos)
                self.display_board()
            elif self.board.is_stop(new_x, new_y):
                self.path.append((new_x, new_y))
                self.display_board()
                print("Gratulacje! Osiągnięto cel!")
                self.end_game()
            else:
                print("Przeszkoda! Wybierz inną ścieżkę.")
        else:
            print("Nie można wyjść poza planszę!")

    def display_board(self):
        board = self.board.get_board()
        result = ""
        for y in range(len(board)):
            for x in range(len(board[y])):
                if board[y][x] == EMPTY:
                    result += ". "
                elif board[y][x] == START:
                    result += "A "
                elif board[y][x] == STOP:
                    result += "B "
                elif board[y][x] == OBSTACLE:
                    result += "X "
                elif board[y][x] == PATH:
                    result += "* "
            result += "\n"
        print(result)

    def end_game(self):
        pygame.quit()
        raise SystemExit

if __name__ == "__main__":
    game = Game(10, 10)
    game.start_game()
