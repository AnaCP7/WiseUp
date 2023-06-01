-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: wiseup
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(255) NOT NULL,
  `option_a` tinytext NOT NULL,
  `option_b` tinytext NOT NULL,
  `option_c` tinytext NOT NULL,
  `option_d` tinytext NOT NULL,
  `answer` char(1) NOT NULL,
  `category` varchar(30) NOT NULL,
  `women` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `question_UNIQUE` (`question`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'Who was an Italian Baroque painter and one of the first women to gain recognition as a painter in her own right?','Rosa Bonheur','Mary Cassatt','Artemisia Gentileschi','Yayoi Kusama','c','art_history',_binary ''),(2,'In which museum is the Mona Lisa located?','Museo del Prado','British Museum','Louvre','Galería Uffizi','c','art',_binary '\0'),(3,'Who was a Dutch painter who was one of the few women accepted as a master in the Guild of Saint Luke in Haarlem?','Joan Mitchell','Georgia O\'Keeffe','Judith Leyster','Amy Sherald','c','art_history',_binary ''),(4,'Who was a French portrait painter who was the official painter of Queen Marie Antoinette?','Elisabeth Louise Vigée Le Brun','Frida Kahlo','Rosa Bonheur','Yayoi Kusama','a','art_history',_binary ''),(5,'Who was a French realist painter known for her paintings of animals, including her famous work, \"The Horse Fair\"?','Amy Sherald','Rosa Bonheur','Georgia O\'Keeffe','Mary Cassatt','b','art_history',_binary ''),(6,'Who was an American Impressionist painter known for her portraits, especially of mothers and children?','Yayoi Kusama','Mary Cassatt','Artemisia Gentileschi','Joan Mitchell','b','art_history',_binary ''),(7,'Who was a Mexican painter known for her self-portraits and works that explored her own experiences, including her physical and emotional pain?','Rosa Bonheur','Judith Leyster','Elisabeth Louise Vigée Le Brun','Frida Kahlo','d','art_history',_binary ''),(8,'Who was an American Abstract Expressionist painter known for her large-scale, colorful paintings?','Amy Sherald','Joan Mitchell','Georgia O\'Keeffe','Mary Cassatt','b','art_history',_binary ''),(9,'Who is a Japanese artist known for her colorful, abstract installations and paintings?','Rosa Bonheur','Artemisia Gentileschi','Judith Leyster','Yayoi Kusama','d','art',_binary ''),(10,'Who is an American portrait painter known for her portraits of African Americans, including the official portrait of former First Lady Michelle Obama?','Amy Sherald','Mary Cassatt','Artemisia Gentileschi','Frida Kahlo','a','art',_binary ''),(11,'Who was a Dutch painter known for his use of vibrant colors and his famous painting \"The Starry Night\"?','Pablo Picasso','Rembrandt','Vincent van Gogh','Salvador Dalí','c','art_history',_binary '\0'),(12,'Who was an Italian painter known for his iconic \"The Birth of Venus\" painting?','Sandro Botticelli','Leonardo da Vinci','Michelangelo','Raphael','a','art_history',_binary '\0'),(13,'Who was an American painter known for her modernist paintings of flowers, New Mexico landscapes, and other subjects?','Georgia O\'Keeffe','Mary Cassatt','Frida Kahlo','Frida Kahlo','a','art_history',_binary ''),(14,'Who was a Spanish painter known for his surrealist works, including \"The Persistence of Memory\"?','Pablo Picasso','Salvador Dalí','Henri Matisse','Claude Monet','b','art_history',_binary '\0'),(15,'Who is credited with the invention of the computer algorithm?','Grace Hopper','Alan Turing','Charles Babbage','Ada Lovelace','d','technology',_binary ''),(16,'Who was a Polish-French physicist and chemist who conducted pioneering research on radioactivity?','Rosalind Franklin','Marie Curie','Ada Lovelace','Rachel Carson','b','science',_binary ''),(17,'Who was a Dutch painter known for his realistic paintings, including \"Girl with a Pearl Earring\"?','Johannes Vermeer','Jan van Eyck','Peter Paul Rubens','Hieronymus Bosch','a','art_history',_binary '\0'),(18,'Who was a Norwegian painter known for his iconic painting \"The Scream\"?','Paul Cézanne','Wassily Kandinsky','Henri Rousseau','Edvard Munch','d','art',_binary '\0'),(19,'Who was an English mathematician and writer known for her work on Charles Babbage\'s early mechanical general-purpose computer, the Analytical Engine?','Margaret Hamilton','Grace Hopper','Katherine Johnson','Ada Lovelace','d','technology',_binary ''),(20,'Who was an Austrian painter known for his decorative paintings, including \"The Kiss\"?','René Magritte','Wassily Kandinsky','Kazimir Malevich','Gustav Klimt','d','art_history',_binary '\0'),(21,'Who was a British primatologist known for her research on the behavior of chimpanzees in Tanzania?','Dian Fossey','Rachel Carson','Jane Goodall','Sylvia Earle','c','science',_binary ''),(22,'Who was a French painter known for his impressionist works, including \"Water Lilies\"?','Édouard Manet','Edgar Degas','Pierre-Auguste Renoir','Claude Monet','d','art_history',_binary '\0'),(23,'Who wrote the novel \"Frankenstein\"?','Mary Shelley','Jane Austen','Emily Bronte','Charlotte Bronte','a','literature',_binary ''),(24,'Who painted \"The Persistence of Memory\" and \"Soft Construction with Boiled Beans\"?','Pablo Picasso','Salvador Dali','Claude Monet','Vincent van Gogh','b','art',_binary '\0'),(25,'Who discovered radium and polonium?','Albert Einstein','Max Planck','Niels Bohr','Marie Curie','d','science',_binary ''),(26,'Who painted \"Girl with a Pearl Earring\"?','Rembrandt','Jan van Eyck','Johannes Vermeer','Peter Paul Rubens','c','art',_binary '\0'),(27,'Who discovered the double helix structure of DNA?','Rosalind Franklin','Linus Pauling','James Watson and Francis Crick','Maurice Wilkins','c','science',_binary '\0'),(28,'Who painted \"The Birth of Venus\" and \"Primavera\"?','Donatello','Leonardo da Vinci','Michelangelo','Sandro Botticelli','d','art',_binary '\0'),(29,'Who is considered the first computer programmer?','Ada Lovelace','Grace Hopper','Katherine Johnson','Dorothy Vaughan','a','technology',_binary ''),(30,'Who painted \"The Kiss\" and \"Portrait of Adele Bloch-Bauer I\"?','Vincent van Gogh','Gustav Klimt','Paul Cezanne','Henri Matisse','b','art',_binary '\0'),(31,'Who is known for her groundbreaking research on chimpanzees and their social behavior?','Jane Goodall','Dian Fossey','Birutė Galdikas','Margaret Mead','a','science',_binary ''),(32,'Who painted \"Water Lilies\" and \"Impression, Sunrise\"?','Claude Monet','Pierre-Auguste Renoir','Edgar Degas','Camille Pissarro','a','art',_binary '\0'),(33,'Who painted \"Les Demoiselles d\'Avignon\" and \"Guernica\"?','Pablo Picasso','Henri Matisse','Georges Braque','Marc Chagall','a','art',_binary '\0'),(34,'Who was a computer scientist and naval officer who is credited with popularizing the term \"debugging\"?','Grace Hopper','Ada Lovelace','Carol Shaw','Hedy Lamarr','a','technology',_binary ''),(35,'Who was a Dutch post-Impressionist painter known for his vibrant use of color and his paintings of sunflowers?','Pablo Picasso','Claude Monet','Vincent van Gogh','Salvador Dalí','c','art_history',_binary '\0'),(36,'Who painted the Mona Lisa?','Pablo Picasso','Leonardo da Vinci','Vincent van Gogh','Michelangelo','b','art',_binary '\0'),(37,'Who painted the Sistine Chapel ceiling?','Pierre-Auguste Renoir','Michelangelo','Claude Monet','Gustav Klimt','b','art',_binary '\0'),(38,'Who is the author of the novel \"Pride and Prejudice\"?','Virginia Woolf','Emily Bronte','Charlotte Bronte','Jane Austen','d','literature',_binary ''),(39,'Who wrote the play \"Romeo and Juliet\"?','Tennessee Williams','Arthur Miller','William Shakespeare','Henrik Ibsen','c','literature',_binary '\0'),(40,'Who is the author of the famous novel \"To Kill a Mockingbird\"?','George Orwell','F. Scott Fitzgerald','Harper Lee','J.D. Salinger','c','literature',_binary '\0'),(41,'What was the first novel ever written?','Thomas Malory, Le Morte d\'Arthur','Geoffroy IV, The Book of the Knight of the Tower','Daniel Defoe, Robinson Crusoe','Murasaki Shikibu, The Tale of Genji','d','literature',_binary '\0'),(42,'Who created the World Wide Web?','Larry Page','Tim Berners-Lee','Vinton Cerf','Steve Jobs','b','technology',_binary '\0');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-01 20:54:23
