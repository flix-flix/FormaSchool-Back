package com.formaschool.back._init;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.formaschool.back.models.Emoji;
import com.formaschool.back.models.Log;
import com.formaschool.back.models.Member;
import com.formaschool.back.models.Message;
import com.formaschool.back.models.Reaction;
import com.formaschool.back.models.Role;
import com.formaschool.back.models.Salon;
import com.formaschool.back.models.Team;
import com.formaschool.back.models.TeamSalonRights;
import com.formaschool.back.models.User;
import com.formaschool.back.repositories.EmojiRepository;
import com.formaschool.back.repositories.LogRepository;
import com.formaschool.back.repositories.MemberRepository;
import com.formaschool.back.repositories.MessageRepository;
import com.formaschool.back.repositories.ReactionRepository;
import com.formaschool.back.repositories.RoleRepository;
import com.formaschool.back.repositories.SalonRepository;
import com.formaschool.back.repositories.TeamRepository;
import com.formaschool.back.repositories.UserRepository;

@RestController
@RequestMapping("init")
public class InitController {

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
			new Team("IBM", "International Business Machines Corporation", "1.png",
					List.of(roles[0], roles[4], roles[5])),
			new Team("Pole emploi", "Invest in Digital People", "2.jpg", List.of(roles[1], roles[6])),
			new Team("M2i", "M2i formations, Hauts-de-France", "3.png", List.of(roles[2])),
			new Team("Semifir", "Ceci est la description de l'équipe Semifir", "4.png", List.of(roles[3])), };

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

	private User[] users = new User[] {
			new User("Félix", "Burie", "123456", "felix@gmail.com", "1.jpg", LocalDate.of(2021, 2, 20)),
			new User("Jason", "Vennin", "azerty", "jason@gmail.com", "2.jpg", LocalDate.of(2021, 2, 25)),
			new User("Luca", "Novelli", "jean-paul2", "JP@gmail.com", "3.jpg", LocalDate.of(2021, 3, 7)),
			new User("Bouchaib", "Faham", "mdp", "bf@gmail.com", "4.jpg", LocalDate.of(2021, 3, 12)), };

	private List<Role> role1() {
		ArrayList<Role> role1 = new ArrayList<Role>();
		role1.add(roles[4]);
		return role1;
	}

	private Member[] members = new Member[] { //
			new Member(null, users[0], teams[0], role1()), new Member(null, users[1], teams[0], new ArrayList<>()),
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

	private HashMap<String, Emoji> emojis = InitEmojis.initEmoji();

	public void initCreatedEmojis() {
		this.emojis.put("bmw", new Emoji(users[2], teams[0], "bmw", "0.png"));
		this.emojis.put("nike", new Emoji(users[2], teams[0], "nike", "1.png"));
		this.emojis.put("insta", new Emoji(users[2], teams[1], "insta", "2.png"));
		this.emojis.put("rocket", new Emoji(users[2], null, "rocket", "3.png"));
		this.emojis.put("bob", new Emoji(users[2], teams[0], "bob", "4.png"));
		this.emojis.put("boby", new Emoji(users[2], teams[0], "boby", "4.png"));
		this.emojis.put("bobu", new Emoji(users[2], teams[0], "bobu", "4.png"));
	}

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
			new Message(members[0], salons[0], "Semaine en présentiel", null, LocalDateTime.of(2021, 4, 28, 7, 37, 11),
					LocalDateTime.of(2021, 4, 28, 7, 37, 11)),
			new Message(members[1], salons[0], "Java ?", null, LocalDateTime.of(2021, 4, 28, 7, 52, 11),
					LocalDateTime.of(2021, 4, 28, 7, 52, 11)),
			new Message(members[2], salons[0], "Regarde sur StackOverflow", null,
					LocalDateTime.of(2021, 4, 28, 7, 52, 35), LocalDateTime.of(2021, 4, 28, 7, 52, 35)),
			new Message(members[3], salons[0], "Regardez ça", null, LocalDateTime.of(2021, 4, 28, 7, 54, 11),
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
			new Reaction(msgs[0], members[0], emojis.get("bagel")), //
			new Reaction(msgs[0], members[0], emojis.get("beer_mug")), //
			new Reaction(msgs[0], members[0], emojis.get("beverage_box")), //
			new Reaction(msgs[0], members[1], emojis.get("bagel")), //

			new Reaction(msgs[11], members[0], emojis.get("grinning_face_with_sweat")), //
			new Reaction(msgs[11], members[2], emojis.get("grinning_face_with_sweat")), //
			new Reaction(msgs[11], members[3], emojis.get("grinning_face_with_sweat")), //

			new Reaction(msgs[13], members[2], emojis.get("OK_hand")), //

			new Reaction(msgs[14], members[0], emojis.get("stop_sign")), //
			new Reaction(msgs[14], members[1], emojis.get("microbe")), //
			new Reaction(msgs[14], members[1], emojis.get("stop_sign")), //

			new Reaction(msgs[15], members[0], emojis.get("fire")), //
			new Reaction(msgs[15], members[2], emojis.get("fire")), //
			new Reaction(msgs[15], members[3], emojis.get("fire")), //
			new Reaction(msgs[15], members[0], emojis.get("flexed_biceps")), //
			new Reaction(msgs[15], members[2], emojis.get("flexed_biceps")), //
			new Reaction(msgs[15], members[3], emojis.get("flexed_biceps")), //

			// Nourriture
			new Reaction(msgs[19], members[2], emojis.get("clinking_beer_mugs")), //
			new Reaction(msgs[19], members[1], emojis.get("beverage_box")), //
			new Reaction(msgs[19], members[3], emojis.get("beverage_box")), //

			new Reaction(msgs[20], members[0], emojis.get("hamburger")), //
			new Reaction(msgs[21], members[0], emojis.get("hamburger")), //

			new Reaction(msgs[2], members[0], emojis.get("beer_mug")), //
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
	private MessageRepository msgRepo;
	@Autowired
	private LogRepository logRepo;
	@Autowired
	private RoleRepository roleRepo;

	// ====================================================================================================

	@GetMapping("drop")
	public void drop() {
		try {
			mongo.getMongoDatabase().drop();
		} catch (NullPointerException e) {
			System.err.println("Can't drop");
		}
		setInitStatus(false);
	}

	@GetMapping("")
	public void init() {
		if (isAlreadyInit())
			throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
		_init();
		setInitStatus(true);
	}

	// ====================================================================================================

	public void _init() {
		for (User user : users)
			userRepo.save(user);
		for (Role role : roles)
			roleRepo.save(role);
		for (Team team : teams)
			teamRepo.save(team);
		for (Salon salon : salons)
			salonRepo.save(salon);

		for (Member member : members)
			memberRepo.save(member);

		for (Message msg : msgs)
			msgRepo.save(msg);

		initCreatedEmojis();
		for (Emoji emoji : emojis.values())
			emojiRepo.save(emoji);

		for (Reaction react : reactions)
			reactRepo.save(react);

		for (Log log : logs)
			logRepo.save(log);
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
}
