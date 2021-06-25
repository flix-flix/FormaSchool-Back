package com.formaschool.back._init;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.formaschool.back.emojis.Emoji;
import com.formaschool.back.emojis.EmojiRepository;
import com.formaschool.back.files.FileModel;
import com.formaschool.back.files.FileRepository;
import com.formaschool.back.files.Folder;
import com.formaschool.back.logs.Log;
import com.formaschool.back.logs.LogRepository;
import com.formaschool.back.members.Member;
import com.formaschool.back.members.MemberRepository;
import com.formaschool.back.messages.Message;
import com.formaschool.back.messages.MessageRepository;
import com.formaschool.back.permissions.Permission;
import com.formaschool.back.permissions.PermissionRepository;
import com.formaschool.back.reactions.Reaction;
import com.formaschool.back.reactions.ReactionRepository;
import com.formaschool.back.rights.TeamSalonRights;
import com.formaschool.back.roles.Role;
import com.formaschool.back.roles.RoleRepository;
import com.formaschool.back.salons.Salon;
import com.formaschool.back.salons.SalonRepository;
import com.formaschool.back.teams.Team;
import com.formaschool.back.teams.TeamRepository;
import com.formaschool.back.users.User;
import com.formaschool.back.users.UserRepository;

public class InitService {

	private FileModel[] files = new FileModel[] { //
			// ========== Shared files ==========
			new FileModel("file_1", "euratechnologies.png"), //
			new FileModel("file_2", "java.jpg"), //
			new FileModel("file_3", "stackoverflow.png"), //
			new FileModel("file_4", "hello.txt"), //

			// ========== Users ==========
			new FileModel("user_1", "user1.jpg"), // 4
			new FileModel("user_2", "user2.jpg"), //
			new FileModel("user_3", "user3.jpg"), //
			new FileModel("user_4", "user4.jpg"), //
			new FileModel("user_5", "user5.jpg"), //
			new FileModel("user_6", "user6.jpg"), //
			new FileModel("user_7", "user7.jpg"), //

			// ========== Teams ==========
			new FileModel("team_1", "team1.png"), // 11
			new FileModel("team_2", "team2.jpg"), //
			new FileModel("team_3", "team3.png"), //
			new FileModel("team_4", "team4.png"), //

			// ========== Emojis orga ==========
			new FileModel("orga_1", "emojisOrga/emojiOrga1.png"), // 15
			new FileModel("orga_2", "emojisOrga/emojiOrga1.png"), //

			// ========== Emojis teams ==========
			new FileModel("team_1_1", "emojisTeams/emojiTeam1_1.png"), // 17
			new FileModel("team_1_2", "emojisTeams/emojiTeam1_2.png"), //
			new FileModel("team_1_3", "emojisTeams/emojiTeam1_3.png"), //

			new FileModel("team_2_1", "emojisTeams/emojiTeam2_1.png"), //

			new FileModel("team_3_1", "emojisTeams/emojiTeam3_1.png"), //

			new FileModel("team_4_1", "emojisTeams/emojiTeam4_1.png"), //
	};

	private User[] users = new User[] {
			new User("Félix", "Burie", "123456", "felix@gmail.com", files[4], LocalDate.of(2021, 2, 20)),
			new User("Jason", "Vennin", "azerty", "jason@gmail.com", files[5], LocalDate.of(2021, 2, 25)),
			new User("Luca", "Novelli", "jean-paul2", "JP@gmail.com", files[6], LocalDate.of(2021, 3, 7)),
			new User("Bouchaib", "Faham", "mdp", "bf@gmail.com", files[7], LocalDate.of(2021, 3, 12)), };

	private TeamSalonRights[] teamSalonRights = new TeamSalonRights[] {
			new TeamSalonRights(true, true, true, true, true, true, true),
			new TeamSalonRights(true, true, true, true, true, true, true),
			new TeamSalonRights(true, true, true, true, true, true, true),
			new TeamSalonRights(true, true, true, true, true, true, true),
			new TeamSalonRights(true, true, true, true, true, true, true),
			new TeamSalonRights(true, true, true, true, true, true, true),
			new TeamSalonRights(true, true, true, true, true, false, true) };

	private Role[] roles = new Role[] {
			new Role("@everyone", "#A2D0EA", teamSalonRights[0], true, true, true, true, true, true),
			new Role("@everyone", "#A2D0EA", teamSalonRights[1], true, true, true, true, true, true),
			new Role("@everyone", "#A2D0EA", teamSalonRights[2], true, true, true, true, true, true),
			new Role("@everyone", "#A2D0EA", teamSalonRights[3], true, true, true, true, true, true),
			new Role("Super_Role", "#fcba03", teamSalonRights[4], true, true, true, true, true, true),
			new Role("Cant_see_logs", "#dbff29", teamSalonRights[5], true, true, true, false, true, true),
			new Role("Cant_send_msg", "#ffD02A", teamSalonRights[6], true, true, true, true, true, true) };

	private Team[] teams = new Team[] {
			new Team("IBM", "International Business Machines Corporation", files[11],
					List.of(roles[0], roles[4], roles[5])),
			new Team("Pole Emploi", "Invest in Digital People", files[12], List.of(roles[1], roles[6])),
			new Team("M2i", "M2i formations, Hauts-de-France", files[13], List.of(roles[2])),
			new Team("Semifir", "Ceci est la description de l'équipe Semifir", files[14], List.of(roles[3])), };

	private Salon[] salons = new Salon[] { new Salon("Général", "Messages en tout genre", teams[0]),
			new Salon("Nourriture:pizza:", "Comment se péter le bide", teams[0]),
			new Salon("Lorem", "Lorem, encore et toujours", teams[0]),
			// IDP
			new Salon("Pole Emploi", "On est là pour vous ! Pôle emploi.", teams[1]),
			// M2i
			new Salon("Secrétaria", "Paperasse paperasse...", teams[2]),

			// Semifir
			new Salon("Java", "Ce concept est à la base du slogan de Sun pour Java : WORA (Write Once, Run Anywhere)",
					teams[3]),
			new Salon("SQL",
					"Le SQL (Structured Query Language) est un langage permettant de communiquer avec une base de données",
					teams[3]),
			new Salon("Angular",
					"Chaque version est prévue pour être compatible avec la version antérieure. Google a promis de faire des mises à jour deux fois par année.",
					teams[3]), };

	private Team[] privTeams = new Team[] { //
			new Team("m p 1", "mg priv 1", null, null), //
			new Team("m p 2", "mg priv 2", null, null), //
			new Team("m p 3", "mg priv 3", null, null),//
	};

	private Salon[] privSalons = new Salon[] { //
			new Salon("Geneneralllll 1", "plop plop plop !", privTeams[0]), //
			new Salon("Geneneralllll 2", "plop plop plop !!", privTeams[1]), //
			new Salon("Geneneralllll 3", "plop plop plop !!!", privTeams[2]), //
	};

	private Member[] privMembers = new Member[] { //
			new Member(null, users[0], teams[0], null), //
			new Member(null, users[1], teams[0], null), //

			new Member(null, users[0], teams[1], null), //
			new Member(null, users[2], teams[1], null), //

			new Member(null, users[1], teams[2], null), //
			new Member(null, users[2], teams[2], null), //
	};

	// ========================###################################################"

	private List<Role> role1() {
		ArrayList<Role> role1 = new ArrayList<Role>();
		role1.add(roles[4]);
		return role1;
	}

	private Member[] members = new Member[] { //
			new Member(null, users[0], teams[0], role1()), //
			new Member(null, users[1], teams[0], new ArrayList<>()),
			new Member(null, users[2], teams[0], new ArrayList<>()),
			new Member(null, users[3], teams[0], new ArrayList<>()),

			new Member(null, users[0], teams[1], new ArrayList<>()),
			new Member(null, users[1], teams[1], new ArrayList<>()),
			new Member(null, users[2], teams[2], new ArrayList<>()),
			new Member(null, users[3], teams[2], new ArrayList<>()),

			new Member(null, users[0], teams[3], new ArrayList<>()),
			new Member(null, users[1], teams[3], new ArrayList<>()),
			new Member(null, users[2], teams[3], new ArrayList<>()),
			new Member(null, users[3], teams[3], new ArrayList<>()), };

	private Permission[] permissions = new Permission[] {
			new Permission(salons[0], members[0], null, new TeamSalonRights(true, true, true, true, true, true, true)),
			new Permission(salons[0], null, roles[4],
					new TeamSalonRights(false, false, false, false, false, false, false)),
			new Permission(salons[0], members[1], null,
					new TeamSalonRights(true, true, true, true, true, true, true)) };

	// ====================================================================================================

	private FileModel[] emojiFiles = Arrays.stream(Folder.EMOJIS_DEFAULT.getFile("").listFiles())
			// -4 -> id ignore '.png'
			.map(file -> new FileModel(file.getName().substring(0, file.getName().length() - 4),
					"emojis/" + file.getName()))
			.toArray(FileModel[]::new);

	private Emoji[] emojis = Arrays.stream(emojiFiles).map(file -> new Emoji(null, null, file.getId(), file))
			.toArray(Emoji[]::new);

	private Map<String, Emoji> _emojis = Arrays.stream(emojis)
			.collect(Collectors.toMap(emoji -> emoji.getPictureFile().getId(), Function.identity()));

	private Emoji[] emojisCreated = new Emoji[] { //
			new Emoji(users[1], null, "m2i", files[15]), //
			new Emoji(users[2], null, "semifir", files[16]), //

			new Emoji(users[2], teams[0], "bob", files[17]), //
			new Emoji(users[1], teams[0], "rl", files[18]), //
			new Emoji(users[3], teams[0], "ibm", files[19]), //
			new Emoji(users[1], teams[1], "pe", files[20]), //
			new Emoji(users[2], teams[2], "nike", files[21]), //
			new Emoji(users[2], teams[3], "bmw", files[22]), //
	};

	// ====================================================================================================

	private Message[] msgs = new Message[] {
			new Message(members[2], salons[0], "Bien ou bien ?", null, LocalDateTime.of(2021, 4, 1, 17, 37, 31),
					LocalDateTime.of(2021, 4, 1, 17, 37, 31)),
			new Message(members[3], salons[0], "trkl", null, LocalDateTime.of(2021, 4, 1, 17, 43, 7),
					LocalDateTime.of(2021, 4, 1, 17, 43, 7)),
			new Message(members[3], salons[0], "Guys ?", null, LocalDateTime.of(2021, 4, 2, 9, 7, 44),
					LocalDateTime.of(2021, 4, 2, 9, 7, 44)),
			new Message(members[0], salons[0], "123**456**789", null, LocalDateTime.of(2021, 4, 2, 9, 7, 46),
					LocalDateTime.of(2021, 4, 2, 9, 7, 46)),
			new Message(members[1], salons[0], "123**456**789**123**456", null, LocalDateTime.of(2021, 4, 2, 9, 8, 1),
					LocalDateTime.of(2021, 4, 2, 9, 8, 1)),
			new Message(members[2], salons[0], "text*italic*text", null, LocalDateTime.of(2021, 4, 2, 9, 8, 27),
					LocalDateTime.of(2021, 4, 2, 9, 8, 27)),
			new Message(members[0], salons[0],
					"Normal\n\n**Bold**\n\n*Italic*\n\n__Under__\n\n~~Strike~~\n\n***__~~All~~__***", null,
					LocalDateTime.of(2021, 4, 5, 17, 18, 19), LocalDateTime.of(2021, 4, 5, 17, 18, 19)),
			new Message(members[2], salons[0], "C'est le feu :fire:\nLe :fire:", null,
					LocalDateTime.of(2021, 4, 28, 0, 50, 11), LocalDateTime.of(2021, 4, 28, 0, 50, 11)),
			new Message(members[1], salons[0], ":victory_hand: EZ !! :victory_hand:", null,
					LocalDateTime.of(2021, 4, 28, 0, 50, 25), LocalDateTime.of(2021, 4, 28, 0, 50, 25)),
			new Message(members[3], salons[0], "C'est le feu :fire:\nC'est le feu :fire:\nC'est le feu\nC'est le feu",
					null, LocalDateTime.of(2021, 4, 28, 0, 50, 11), LocalDateTime.of(2021, 4, 28, 0, 50, 11)),
			new Message(members[0], salons[0], "Semaine en présentiel", files[0],
					LocalDateTime.of(2021, 4, 28, 7, 37, 11), LocalDateTime.of(2021, 4, 28, 7, 37, 11)),
			new Message(members[1], salons[0], "Java ?", files[1], LocalDateTime.of(2021, 4, 28, 7, 52, 11),
					LocalDateTime.of(2021, 4, 28, 7, 52, 11)),
			new Message(members[2], salons[0], "Regarde sur StackOverflow", files[2],
					LocalDateTime.of(2021, 4, 28, 7, 52, 35), LocalDateTime.of(2021, 4, 28, 7, 52, 35)),
			new Message(members[3], salons[0], "Regardez ça", files[3], LocalDateTime.of(2021, 4, 28, 7, 54, 11),
					LocalDateTime.of(2021, 4, 28, 7, 54, 11)),
			new Message(members[0], salons[0], "**Distanciation               <->               Sociale**", null,
					LocalDateTime.of(2021, 5, 5, 23, 1, 7), LocalDateTime.of(2021, 5, 5, 23, 1, 7)),
			new Message(members[1], salons[0], ":victory_hand: :rl: **>** :m2i: :victory_hand:", null,
					LocalDateTime.of(2021, 5, 5, 23, 1, 7), LocalDateTime.of(2021, 5, 5, 23, 1, 7)),

			new Message(members[0], salons[1], ":beer_mug: :wine_glass: :cocktail_glass:", null,
					LocalDateTime.of(2021, 4, 1, 17, 35, 21), LocalDateTime.of(2021, 4, 1, 17, 35, 21)),
			new Message(members[1], salons[1], "Frites ! :french_fries: :french_fries:", null,
					LocalDateTime.of(2021, 4, 4, 9, 27, 7), LocalDateTime.of(2021, 4, 4, 9, 27, 7)),
			new Message(members[3], salons[1], ":hamburger: Hamburger :hamburger:", null,
					LocalDateTime.of(2021, 4, 4, 10, 1, 21), LocalDateTime.of(2021, 4, 4, 10, 1, 21)),
			new Message(members[2], salons[1], "P'tit dej' :cookie: :croissant:", null,
					LocalDateTime.of(2021, 4, 5, 7, 12, 35), LocalDateTime.of(2021, 4, 5, 7, 12, 35)),

			new Message(members[0], salons[2],
					"    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.\n"
							+ "    Ut velit mauris, egestas sed, gravida nec, ornare ut, mi. Aenean ut orci vel massa suscipit pulvinar. Nulla sollicitudin. Fusce varius, ligula non tempus aliquam, nunc turpis ullamcorper nibh, in tempus sapien eros vitae ligula. Pellentesque rhoncus nunc et augue. Integer id felis. Curabitur aliquet pellentesque diam. Integer quis metus vitae elit lobortis egestas. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi vel erat non mauris convallis vehicula. Nulla et sapien. Integer tortor tellus, aliquam faucibus, convallis id, congue eu, quam. Mauris ullamcorper felis vitae erat. Proin feugiat, augue non elementum posuere, metus purus iaculis lectus, et tristique ligula justo vitae magna.\n"
							+ "    Aliquam convallis sollicitudin purus. Praesent aliquam, enim at fermentum mollis, ligula massa adipiscing nisl, ac euismod nibh nisl eu lectus. Fusce vulputate sem at sapien. Vivamus leo. Aliquam euismod libero eu enim. Nulla nec felis sed leo placerat imperdiet. Aenean suscipit nulla in justo. Suspendisse cursus rutrum augue. Nulla tincidunt tincidunt mi. Curabitur iaculis, lorem vel rhoncus faucibus, felis magna fermentum augue, et ultricies lacus lorem varius purus. Curabitur eu amet.",
					null, LocalDateTime.of(2021, 9, 10, 22, 11, 0), LocalDateTime.of(2021, 9, 10, 22, 11, 0)),

			// === Pole Emploi ===
			new Message(members[0], salons[3], "Bonjour", null, LocalDateTime.of(2021, 1, 5, 7, 7, 35),
					LocalDateTime.of(2021, 1, 5, 7, 7, 35)),
			new Message(members[2], salons[3], "Salut", null, LocalDateTime.of(2021, 2, 5, 9, 12, 43),
					LocalDateTime.of(2021, 2, 5, 9, 12, 43)),
			new Message(members[1], salons[3], "Coucou", null, LocalDateTime.of(2021, 4, 5, 15, 17, 45),
					LocalDateTime.of(2021, 4, 5, 15, 17, 45)),
			new Message(members[1], salons[3], ":rl: :rl:", null, LocalDateTime.of(2021, 4, 5, 15, 18, 22),
					LocalDateTime.of(2021, 4, 5, 15, 18, 22)),
			new Message(members[1], salons[3], ":pe: **>** :m2i:", null, LocalDateTime.of(2021, 4, 5, 15, 18, 33),
					LocalDateTime.of(2021, 4, 5, 15, 18, 33)),

			// === M2i ===
			new Message(members[3], salons[4], "Tuto pour le __souligné__", null,
					LocalDateTime.of(2021, 4, 2, 19, 47, 35), LocalDateTime.of(2021, 4, 2, 19, 47, 35)),
			new Message(members[2], salons[4], "et celui pour l'*italique*", null,
					LocalDateTime.of(2021, 4, 5, 7, 12, 35), LocalDateTime.of(2021, 4, 5, 7, 12, 35)),

			// === ===
			new Message(members[1], salons[5],
					"List<String> teams = new ArrayList<>(List.of(\"FormaSchool\", \"JDR\", \"Médicaments\", \"Tinder Jeux\"));",
					null, LocalDateTime.of(2021, 4, 5, 7, 12, 35), LocalDateTime.of(2021, 4, 5, 7, 12, 35)),
			new Message(members[0], salons[5], "String nbTeams = \"\" + teams.size();", null,
					LocalDateTime.of(2021, 4, 5, 7, 13, 35), LocalDateTime.of(2021, 4, 5, 7, 13, 35)),
			new Message(members[3], salons[5], "System.out.println(String.join(\", \", lilist));", null,
					LocalDateTime.of(2021, 4, 5, 7, 13, 36), LocalDateTime.of(2021, 4, 5, 7, 13, 36)),
			new Message(members[2], salons[5], "teams.stream().forEach(System.out::println);", null,
					LocalDateTime.of(2021, 4, 5, 7, 13, 37), LocalDateTime.of(2021, 4, 5, 7, 13, 37)),
			new Message(members[2], salons[6], "**SELECT** * **FROM** Teams", null,
					LocalDateTime.of(2021, 4, 5, 23, 51, 35), LocalDateTime.of(2021, 4, 5, 23, 51, 35)),
			new Message(members[1], salons[6], "**DROP DATABASE** FormaSchool", null,
					LocalDateTime.of(2021, 4, 5, 23, 51, 36), LocalDateTime.of(2021, 4, 5, 23, 51, 36)),
			new Message(members[2], salons[6],
					"Savoir effectuer des requêtes n’est pas trop difficile, mais il convient de véritablement comprendre comment fonctionne le stockage des données et la façon dont elles sont lues pour **__optimiser les performances__**. Les optimisations sont basées dans 2 catégories: les bons choix à faire lorsqu’il faut définir la **__structure de la base de données__** et les méthodes les plus adaptées pour **__lire les données__**.",
					null, LocalDateTime.of(2021, 4, 5, 23, 52, 37), LocalDateTime.of(2021, 4, 5, 23, 52, 37)),
			new Message(members[1], salons[6], "`SELECT`, `FROM`, `DROP`, ...", null,
					LocalDateTime.of(2021, 4, 5, 23, 51, 36), LocalDateTime.of(2021, 4, 5, 23, 51, 36)),
			new Message(members[3], salons[6], "Exemple de requete:\n" + //
					"```SELECT * FROM table\n" + //
					"WHERE .... .. ....\n" + //
					"GROUP .. .... ....\n" + //
					"ORDER BY .. ... ..```Voili voila :)", null, LocalDateTime.of(2021, 4, 5, 23, 51, 36),
					LocalDateTime.of(2021, 4, 5, 23, 51, 36)),
			new Message(members[3], salons[7], "**@Input()** team", null, LocalDateTime.of(2021, 4, 5, 7, 1, 35),
					LocalDateTime.of(2021, 4, 5, 7, 1, 35)),
			new Message(members[2], salons[7], "**routerLink**=\"/404\"", null, LocalDateTime.of(2021, 4, 5, 12, 2, 36),
					LocalDateTime.of(2021, 4, 5, 12, 2, 36)) };

	private Reaction[] reactions = new Reaction[] { //
			new Reaction(msgs[0], members[0], _emojis.get("bagel")), //
			new Reaction(msgs[0], members[0], _emojis.get("beer_mug")), //
			new Reaction(msgs[0], members[0], _emojis.get("beverage_box")), //
			new Reaction(msgs[0], members[1], _emojis.get("bagel")), //

			new Reaction(msgs[11], members[0], _emojis.get("grinning_face_with_sweat")), //
			new Reaction(msgs[11], members[2], _emojis.get("grinning_face_with_sweat")), //
			new Reaction(msgs[11], members[3], _emojis.get("grinning_face_with_sweat")), //

			new Reaction(msgs[13], members[2], _emojis.get("OK_hand")), //

			new Reaction(msgs[14], members[0], _emojis.get("stop_sign")), //
			new Reaction(msgs[14], members[1], _emojis.get("microbe")), //
			new Reaction(msgs[14], members[1], _emojis.get("stop_sign")), //

			new Reaction(msgs[15], members[0], _emojis.get("fire")), //
			new Reaction(msgs[15], members[2], _emojis.get("fire")), //
			new Reaction(msgs[15], members[3], _emojis.get("fire")), //
			new Reaction(msgs[15], members[0], _emojis.get("flexed_biceps")), //
			new Reaction(msgs[15], members[2], _emojis.get("flexed_biceps")), //
			new Reaction(msgs[15], members[3], _emojis.get("flexed_biceps")), //

			// Nourriture
			new Reaction(msgs[19], members[2], _emojis.get("clinking_beer_mugs")), //
			new Reaction(msgs[19], members[1], _emojis.get("beverage_box")), //
			new Reaction(msgs[19], members[3], _emojis.get("beverage_box")), //

			new Reaction(msgs[20], members[0], _emojis.get("hamburger")), //
			new Reaction(msgs[21], members[0], _emojis.get("hamburger")), //

			new Reaction(msgs[2], members[0], _emojis.get("beer_mug")), //
	};

	private Log[] logs = new Log[] {
			new Log(null, users[2], teams[0], 0, LocalDateTime.of(2019, 1, 16, 17, 18, 19), "a crée l'emoji Kama"),
			new Log(null, users[1], teams[0], 6, LocalDateTime.of(2021, 5, 4, 13, 17, 19),
					"a épinglé un message de Bouchaib dans Géneral"),
			new Log(null, users[1], teams[0], 3, LocalDateTime.of(2021, 5, 4, 15, 17, 19), "a crée un salon"),
			new Log(null, users[1], teams[0], 5, LocalDateTime.of(2021, 5, 4, 9, 53, 19), "a supprimé un salon"),
			new Log(null, users[2], teams[0], 4, LocalDateTime.of(2021, 2, 4, 6, 53, 29), "a modifie un salon"),
			new Log(null, users[2], null, 8, LocalDateTime.of(2021, 6, 5, 9, 53, 29),
					"a créer l'utilisateur Benoit Routier"),
			new Log(null, users[2], teams[0], 1, LocalDateTime.of(2019, 1, 16, 9, 53, 29), "a Modifier l'emoji Kama"),
			new Log(null, users[2], null, 11, LocalDateTime.of(2019, 1, 16, 7, 53, 29), "a crée l'equipe Dofus") };

	// ====================================================================================================

	@Autowired
	private MongoDatabaseFactory mongo;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TeamRepository teamRepo;
	@Autowired
	private SalonRepository salonRepo;
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private EmojiRepository emojiRepo;
	@Autowired
	private ReactionRepository reactRepo;
	@Autowired
	private FileRepository fileRepo;
	@Autowired
	private MessageRepository msgRepo;
	@Autowired
	private LogRepository logRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private PermissionRepository permissionRepo;

	// ====================================================================================================

	public void drop() {
		try {
			mongo.getMongoDatabase().drop();
		} catch (NullPointerException e) {
			System.err.println("Can't drop");
		}
		setInitStatus(false);
	}

	public void init() {
		if (isAlreadyInit())
			throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);

		System.out.println(">>> Init Mongo");
		saveTime();

		Arrays.stream(users).forEach(obj -> userRepo.save(obj));
		Arrays.stream(roles).forEach(obj -> roleRepo.save(obj));
		Arrays.stream(teams).forEach(obj -> teamRepo.save(obj));
		Arrays.stream(salons).forEach(obj -> salonRepo.save(obj));
		Arrays.stream(members).forEach(obj -> memberRepo.save(obj));
		Arrays.stream(permissions).forEach(obj -> permissionRepo.save(obj));
		Arrays.stream(files).forEach(obj -> fileRepo.save(obj));

		Arrays.stream(privTeams).forEach(obj -> teamRepo.save(obj));
		Arrays.stream(privSalons).forEach(obj -> salonRepo.save(obj));
		Arrays.stream(privMembers).forEach(obj -> memberRepo.save(obj));

		Arrays.stream(msgs).forEach(obj -> msgRepo.save(obj));
		timeInfo("Before emojis");
		Arrays.stream(emojiFiles).forEach(obj -> fileRepo.save(obj));
		timeInfo("Emojis files");
		Arrays.stream(emojis).forEach(obj -> emojiRepo.save(obj));
		timeInfo("Emojis");
		Arrays.stream(emojisCreated).forEach(obj -> emojiRepo.save(obj));
		Arrays.stream(reactions).forEach(obj -> reactRepo.save(obj));
		Arrays.stream(logs).forEach(obj -> logRepo.save(obj));

		setInitStatus(true);
	}

	// ====================================================================================================

	public boolean isAlreadyInit() {
		return new File("alreadyInit").exists();
	}

	public void setInitStatus(boolean exist) {
		if (exist)
			try {
				File file = new File("alreadyInit");
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		else
			new File("alreadyInit").delete();
	}

	// ====================================================================================================

	static double time = -1;

	/** Save current time in memory */
	public static void saveTime() {
		time = System.currentTimeMillis();
	}

	/**
	 * Display the message with the time elapsed since last call to
	 * {@link #saveTime()} or {@link #timeInfo(String)}
	 */
	public static void timeInfo(String str) {
		System.out.println(String.format(">>> " + str + " (%.3fs)", (System.currentTimeMillis() - time) / 1000));
		saveTime();
	}
}
