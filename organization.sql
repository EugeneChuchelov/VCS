-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Ноя 14 2018 г., 18:14
-- Версия сервера: 5.6.41
-- Версия PHP: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `organization`
--

-- --------------------------------------------------------

--
-- Структура таблицы `departments`
--

CREATE TABLE `departments` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `Description` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `departments`
--

INSERT INTO `departments` (`id`, `name`, `Description`) VALUES
(1, 'Managing', 'Rules all over'),
(2, 'First Engineering', 'Fixing computers'),
(3, 'Second Engineering', 'Fixing printers'),
(10, 'Defencing', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `employees`
--

CREATE TABLE `employees` (
  `id` int(10) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `second_name` varchar(50) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `hire_date` date NOT NULL,
  `salary` decimal(15,5) NOT NULL,
  `jobtitles_id` int(10) NOT NULL,
  `departments_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `employees`
--

INSERT INTO `employees` (`id`, `first_name`, `second_name`, `birth_date`, `hire_date`, `salary`, `jobtitles_id`, `departments_id`) VALUES
(1, 'Igor', 'Teleastrov', '1978-04-11', '2016-12-24', '60001.00000', 5, 2),
(2, 'Vladimir', 'Dikobrasov', '1991-12-22', '2017-02-13', '31000.00000', 2, 1),
(3, 'Natalia', 'Prigorelova', '1991-09-17', '2011-04-11', '36000.00000', 4, 1),
(4, 'Petr', 'Stroibatov', '1987-11-01', '2017-07-30', '26000.00000', 4, 1),
(5, 'Alisa', 'Karindos', '1971-01-30', '2012-08-21', '23000.00000', 3, 2),
(6, 'Oleg', 'Armorov', '1981-06-14', '2014-04-15', '20000.00000', 3, 2),
(7, 'Sirgay', 'Prokopenko', '1965-11-08', '2014-09-19', '21000.00000', 3, 2),
(8, 'Patrikey', 'Evlampov', '1992-11-20', '2017-11-24', '24000.00000', 3, 3),
(9, 'Oleg', 'Petrenko', '1988-12-12', '2009-05-15', '30000.00000', 3, 3),
(10, 'Oleg', 'Twaikov', '2018-11-06', '2018-11-29', '11000.00000', 4, 3),
(11, 'Svetlogor', 'Dianatov', NULL, '1211-05-11', '125000.00000', 4, 10),
(12, 'Yaropolk', 'Remnev', NULL, '1015-04-29', '201000.00000', 4, 10);

-- --------------------------------------------------------

--
-- Структура таблицы `jobtitles`
--

CREATE TABLE `jobtitles` (
  `id` int(10) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `jobtitles`
--

INSERT INTO `jobtitles` (`id`, `name`) VALUES
(1, 'assistant'),
(3, 'engineer'),
(5, 'head'),
(4, 'manager'),
(2, 'secretary');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Индексы таблицы `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employees_ibfk_1` (`jobtitles_id`),
  ADD KEY `employees_ibfk_2` (`departments_id`);

--
-- Индексы таблицы `jobtitles`
--
ALTER TABLE `jobtitles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `departments`
--
ALTER TABLE `departments`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT для таблицы `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT для таблицы `jobtitles`
--
ALTER TABLE `jobtitles`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`jobtitles_id`) REFERENCES `jobtitles` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `employees_ibfk_2` FOREIGN KEY (`departments_id`) REFERENCES `departments` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
