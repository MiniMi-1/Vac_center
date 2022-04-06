-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Creato il: Set 08, 2021 alle 20:47
-- Versione del server: 5.7.32
-- Versione PHP: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `SCHIERA`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `coda_vaccino`
--

CREATE TABLE `coda_vaccino` (
  `ref_prenotazione` int(15) NOT NULL,
  `is_servito` tinyint(1) NOT NULL,
  `coda` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `coda_vaccino`
--

INSERT INTO `coda_vaccino` (`ref_prenotazione`, `is_servito`, `coda`) VALUES
(8, 1, 1),
(9, 1, 2),
(12, 1, 5);

-- --------------------------------------------------------

--
-- Struttura della tabella `dipendente`
--

CREATE TABLE `dipendente` (
  `cod_impiegato` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_asp` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `dipendente`
--

INSERT INTO `dipendente` (`cod_impiegato`, `nome`, `cognome`, `is_staff`, `is_asp`) VALUES
(1, 'staff', 'staff', 1, 0),
(2, 'asp', 'asp', 0, 1),
(1111, 'Mario', 'Rossi', 0, 0),
(1924, 'Lorenzo', 'Medici', 0, 0),
(8391, 'Luigi', 'Verdi', 0, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `prenotazione`
--

CREATE TABLE `prenotazione` (
  `cod_prenotazione` int(15) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `codice_fiscale` varchar(255) NOT NULL,
  `is_prima_dose` tinyint(1) DEFAULT NULL,
  `is_completo` tinyint(1) DEFAULT NULL,
  `is_rifiutata` tinyint(1) DEFAULT NULL,
  `tipo_vaccino` varchar(255) DEFAULT NULL,
  `malattie` text NOT NULL,
  `data_vaccino` date NOT NULL,
  `data_prima_dose` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `prenotazione`
--

INSERT INTO `prenotazione` (`cod_prenotazione`, `nome`, `cognome`, `codice_fiscale`, `is_prima_dose`, `is_completo`, `is_rifiutata`, `tipo_vaccino`, `malattie`, `data_vaccino`, `data_prima_dose`) VALUES
(8, 'Pinto', 'Panto', 'PINTOPANTO123', 0, 1, 0, 'johnson_and_johnson', 'Oki', '2021-09-08', '2021-09-08'),
(9, 'Prova', 'Test8', 'PROVATEST', 0, 0, 0, 'pfizer', '', '2021-10-20', '2021-09-08'),
(10, 'Luigi', 'Verdi', 'VRDLGU98A01A001H', 1, 0, 0, NULL, '', '2021-09-09', NULL),
(11, 'Mario', 'Rossi', 'RSSMRA14A01A001M', 1, 0, 0, NULL, 'Antibiotici', '2021-09-09', NULL),
(12, 'Valentino', 'Rossi', 'RSSVNT14A01A001A', 0, 0, 0, 'moderna', '', '2021-10-20', '2021-09-08');

-- --------------------------------------------------------

--
-- Struttura della tabella `vacc_effettuata`
--

CREATE TABLE `vacc_effettuata` (
  `ref_prenotazione` int(15) NOT NULL,
  `ref_dipendente` int(11) NOT NULL,
  `data_vaccino` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `vacc_effettuata`
--

INSERT INTO `vacc_effettuata` (`ref_prenotazione`, `ref_dipendente`, `data_vaccino`) VALUES
(8, 1111, '2021-09-08'),
(9, 1111, '2021-09-08'),
(12, 1111, '2021-09-08');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `coda_vaccino`
--
ALTER TABLE `coda_vaccino`
  ADD PRIMARY KEY (`coda`),
  ADD KEY `ref_prenotazione` (`ref_prenotazione`);

--
-- Indici per le tabelle `dipendente`
--
ALTER TABLE `dipendente`
  ADD PRIMARY KEY (`cod_impiegato`);

--
-- Indici per le tabelle `prenotazione`
--
ALTER TABLE `prenotazione`
  ADD PRIMARY KEY (`cod_prenotazione`);

--
-- Indici per le tabelle `vacc_effettuata`
--
ALTER TABLE `vacc_effettuata`
  ADD PRIMARY KEY (`ref_prenotazione`,`ref_dipendente`,`data_vaccino`),
  ADD KEY `ref_dipendente` (`ref_dipendente`),
  ADD KEY `ref_prenotazione` (`ref_prenotazione`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `coda_vaccino`
--
ALTER TABLE `coda_vaccino`
  MODIFY `coda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `prenotazione`
--
ALTER TABLE `prenotazione`
  MODIFY `cod_prenotazione` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `coda_vaccino`
--
ALTER TABLE `coda_vaccino`
  ADD CONSTRAINT `coda_vaccino_ibfk_1` FOREIGN KEY (`ref_prenotazione`) REFERENCES `prenotazione` (`cod_prenotazione`);

--
-- Limiti per la tabella `vacc_effettuata`
--
ALTER TABLE `vacc_effettuata`
  ADD CONSTRAINT `dipendente` FOREIGN KEY (`ref_dipendente`) REFERENCES `dipendente` (`cod_impiegato`),
  ADD CONSTRAINT `prenotazione` FOREIGN KEY (`ref_prenotazione`) REFERENCES `prenotazione` (`cod_prenotazione`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
