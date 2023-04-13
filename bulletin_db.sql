-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Mar 15 Juin 2021 à 11:39
-- Version du serveur: 5.5.16
-- Version de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `bulletin_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `tb_eleve`
--

CREATE TABLE IF NOT EXISTS `tb_eleve` (
  `num_eleve` int(11) NOT NULL,
  `nom` varchar(150) NOT NULL,
  `classe` varchar(30) NOT NULL,
  `sexe` varchar(20) NOT NULL,
  PRIMARY KEY (`num_eleve`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `tb_eleve`
--

INSERT INTO `tb_eleve` (`num_eleve`, `nom`, `classe`, `sexe`) VALUES
(1, 'KAGONBE EZECHIEL', '6EME', 'MASCULIN'),
(2, 'DJASNAN RICHARD', '6EME', 'MASCULIN'),
(3, 'FATIME BRAHIM', '6EME', 'FEMININ'),
(4, 'KOUTOU JOSIANE', '6EME', 'FEMININ'),
(5, 'DJIMASRA ELVIS', '6EME', 'MASCULIN'),
(6, 'NETOUA ANGEL', '6EME', 'FEMININ'),
(7, 'ABDOULAYE SALEH', '6EME', 'MASCULIN'),
(8, 'SERGE ALAIN', '5EME', 'MASCULIN');

-- --------------------------------------------------------

--
-- Structure de la table `tb_matiere`
--

CREATE TABLE IF NOT EXISTS `tb_matiere` (
  `nom_mat` varchar(100) NOT NULL,
  `coeff` smallint(6) NOT NULL,
  PRIMARY KEY (`nom_mat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `tb_matiere`
--

INSERT INTO `tb_matiere` (`nom_mat`, `coeff`) VALUES
('Anglais', 2),
('Art', 2),
('Français', 3),
('HG', 2),
('IC', 1),
('MATH', 4),
('PC', 3),
('SVT', 3);

-- --------------------------------------------------------

--
-- Structure de la table `tb_notes`
--

CREATE TABLE IF NOT EXISTS `tb_notes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num_elv` int(11) NOT NULL,
  `matiere` varchar(100) NOT NULL,
  `devoir1` decimal(4,2) DEFAULT NULL,
  `devoir2` decimal(4,2) DEFAULT NULL,
  `compo` decimal(4,2) DEFAULT NULL,
  `trimestre` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk1` (`num_elv`),
  KEY `fk2` (`matiere`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=36 ;

--
-- Contenu de la table `tb_notes`
--

INSERT INTO `tb_notes` (`id`, `num_elv`, `matiere`, `devoir1`, `devoir2`, `compo`, `trimestre`) VALUES
(3, 1, 'Anglais', '11.50', '11.00', '13.50', 1),
(5, 1, 'Art', '17.00', '16.00', '18.50', 1),
(6, 1, 'Français', '12.00', '10.00', '14.00', 1),
(7, 1, 'HG', '16.00', '17.00', '12.75', 1),
(8, 1, 'IC', '16.00', '15.00', '13.00', 1),
(9, 1, 'MATH', '14.00', '19.00', '11.00', 1),
(10, 1, 'PC', '8.00', '10.00', '9.00', 1),
(11, 1, 'SVT', '14.00', '8.00', '12.00', 1),
(12, 2, 'PC', '10.00', '7.00', '4.00', 1),
(13, 2, 'MATH', '13.00', '11.00', '9.00', 1),
(14, 2, 'SVT', '12.00', '15.00', '17.00', 1),
(15, 2, 'Anglais', '13.00', '10.00', '12.50', 1),
(16, 2, 'Art', '14.00', '18.00', '15.00', 1),
(17, 2, 'Français', '10.00', '12.00', '11.00', 1),
(18, 2, 'HG', '7.00', '12.00', '10.00', 1),
(19, 2, 'IC', '16.00', '13.00', '14.00', 1),
(20, 3, 'Anglais', '15.00', '17.00', '14.50', 1),
(21, 3, 'Art', '19.00', '17.00', '18.00', 1),
(22, 3, 'Français', '14.00', '13.50', '13.00', 1),
(23, 3, 'HG', '17.00', '15.00', '18.00', 1),
(24, 3, 'IC', '15.00', '16.00', '14.00', 1),
(25, 3, 'MATH', '12.00', '17.50', '16.00', 1),
(26, 3, 'PC', '16.00', '14.00', '11.00', 1),
(27, 3, 'SVT', '17.00', '18.00', '19.50', 1),
(28, 8, 'Anglais', '12.00', '15.00', '12.50', 1),
(29, 8, 'Art', '17.00', '15.00', '18.00', 1),
(30, 8, 'Français', '12.00', '11.00', '14.00', 1),
(31, 8, 'HG', '10.00', '8.00', '11.00', 1),
(32, 8, 'IC', '14.00', '13.00', '10.00', 1),
(33, 8, 'MATH', '9.00', '8.00', '11.00', 1),
(34, 8, 'PC', '13.00', '11.00', '10.50', 1),
(35, 8, 'SVT', '9.00', '14.00', '12.00', 1);

-- --------------------------------------------------------

--
-- Structure de la table `tb_rang`
--

CREATE TABLE IF NOT EXISTS `tb_rang` (
  `rang` smallint(6) NOT NULL AUTO_INCREMENT,
  `classe` varchar(20) DEFAULT NULL,
  `num_elv` int(11) DEFAULT NULL,
  `moyenne` decimal(4,2) DEFAULT NULL,
  `trimestre` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`rang`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `tb_rang`
--

INSERT INTO `tb_rang` (`rang`, `classe`, `num_elv`, `moyenne`, `trimestre`) VALUES
(1, '6EME', 3, '15.57', 1),
(2, '6EME', 1, '12.86', 1),
(3, '6EME', 2, '11.41', 1);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `v2_notes`
--
CREATE TABLE IF NOT EXISTS `v2_notes` (
`classe` varchar(30)
,`trimestre` smallint(6)
,`matiere` varchar(100)
,`num_elv` int(11)
,`moyds` decimal(6,2)
,`compo` decimal(4,2)
,`moydeux` decimal(7,2)
,`coeff` smallint(6)
,`moycoeff` decimal(12,2)
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `v_notes`
--
CREATE TABLE IF NOT EXISTS `v_notes` (
`matiere` varchar(100)
,`coeff` smallint(6)
,`num_elv` int(11)
,`nom` varchar(150)
,`sexe` varchar(20)
,`classe` varchar(30)
,`devoir1` decimal(4,2)
,`devoir2` decimal(4,2)
,`compo` decimal(4,2)
,`trimestre` smallint(6)
,`id` int(11)
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `v_rang`
--
CREATE TABLE IF NOT EXISTS `v_rang` (
`classe` varchar(30)
,`trimestre` smallint(6)
,`num_elv` int(11)
,`moyg` decimal(35,2)
);
-- --------------------------------------------------------

--
-- Structure de la vue `v2_notes`
--
DROP TABLE IF EXISTS `v2_notes`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v2_notes` AS select `v_notes`.`classe` AS `classe`,`v_notes`.`trimestre` AS `trimestre`,`v_notes`.`matiere` AS `matiere`,`v_notes`.`num_elv` AS `num_elv`,round(((`v_notes`.`devoir1` + `v_notes`.`devoir2`) / 2),2) AS `moyds`,`v_notes`.`compo` AS `compo`,round(((((`v_notes`.`devoir1` + `v_notes`.`devoir2`) / 2) + `v_notes`.`compo`) / 2),2) AS `moydeux`,`v_notes`.`coeff` AS `coeff`,(round(((((`v_notes`.`devoir1` + `v_notes`.`devoir2`) / 2) + `v_notes`.`compo`) / 2),2) * `v_notes`.`coeff`) AS `moycoeff` from `v_notes`;

-- --------------------------------------------------------

--
-- Structure de la vue `v_notes`
--
DROP TABLE IF EXISTS `v_notes`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_notes` AS select `tb_notes`.`matiere` AS `matiere`,`tb_matiere`.`coeff` AS `coeff`,`tb_notes`.`num_elv` AS `num_elv`,`tb_eleve`.`nom` AS `nom`,`tb_eleve`.`sexe` AS `sexe`,`tb_eleve`.`classe` AS `classe`,`tb_notes`.`devoir1` AS `devoir1`,`tb_notes`.`devoir2` AS `devoir2`,`tb_notes`.`compo` AS `compo`,`tb_notes`.`trimestre` AS `trimestre`,`tb_notes`.`id` AS `id` from ((`tb_eleve` join `tb_notes` on((`tb_eleve`.`num_eleve` = `tb_notes`.`num_elv`))) join `tb_matiere` on((`tb_notes`.`matiere` = `tb_matiere`.`nom_mat`)));

-- --------------------------------------------------------

--
-- Structure de la vue `v_rang`
--
DROP TABLE IF EXISTS `v_rang`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_rang` AS select `v2_notes`.`classe` AS `classe`,`v2_notes`.`trimestre` AS `trimestre`,`v2_notes`.`num_elv` AS `num_elv`,round((sum(`v2_notes`.`moycoeff`) / sum(`v2_notes`.`coeff`)),2) AS `moyg` from `v2_notes` group by `v2_notes`.`num_elv`,`v2_notes`.`trimestre`;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `tb_notes`
--
ALTER TABLE `tb_notes`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`num_elv`) REFERENCES `tb_eleve` (`num_eleve`),
  ADD CONSTRAINT `fk2` FOREIGN KEY (`matiere`) REFERENCES `tb_matiere` (`nom_mat`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
