package com.ios.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ios.model.Posts;

@Service
public class PostsService {

	public List<Posts> getPosts() {

		return new ArrayList<>(

				Arrays.asList(new Posts(5551, 501243,
						"Placeat averto cavus sortitus venustas solutio caecus venio accipio cernuus argumentum.",
						"Ascit talus torrens. Maxime iure sit. Aegrotatio et absens. Thermae sono repellendus. Uterque curto coepi. Admoveo coma centum. Supplanto adiuvo aggredior. Debeo angustus decimus. Vetus angelus correptius. Sollicito explicabo pecunia. Certus cauda ullam. Cattus theatrum sponte. Decor color strues. Ab cognomen complectus. Eveniet verumtamen totam. Vero pecto depono. Tabella adsidue pariatur. Subito demergo callide. Trepide quis cimentarius. Aveho amo vitium. Cum quis certe."),
						new Posts(5549, 501239, "Bene tabernus centum ambulo aperte eum a crapula.",
								"Quis territo aro. Velit vulpes sollicito. Creo sumo vehemens. Animadverto sulum tristis. Calamitas tametsi ultra. Cruciamentum thesaurus deprimo. Unde consequatur vinitor. Viduo aliqua commodi. Nam ciminatio dignissimos. Ambitus avaritia inventore. Repellendus volutabrum aiunt. Vis cognomen vito. Appositus adflicto cerno. Circumvenio beatae vitium. Quae quibusdam viriliter. Sperno synagoga stultus."),
						new Posts(5542, 501239,
								"Uberrime ambitus teres coma cubitum cohibeo inflammatio valens varius consequatur addo curtus angelus solum decumbo.",
								"Vito denuncio denego. Universe veritatis volubilis. Quia cometes sodalitas. Aspicio patrocinor sui. Veritas tredecim tersus. Quos deserunt vulnero. Careo curvus ea. Adipisci patrocinor constans. Tenus balbus cilicium. Adicio sed blandior. Brevis sollers aut. Aliquam conitor acidus. Est deputo pauper. Officiis angelus cupressus. Celer carpo acervus. Ipsum cresco aranea."),
						new Posts(5541, 500254,
								"Defaeco valens perferendis autem adinventitias circumvenio canonicus cavus eum decor spero cresco aestivus.",
								"Sonitus autem damnatio. Corporis uterque denuncio. Terreo laudantium vel. Depopulo depereo votum. Atavus vesco esse. Amiculum thermae vergo. Assentator consequatur arguo. Uxor delicate delego. Damno architecto neque. Tui desolo vitium. Claustrum acquiro audax. Crepusculum argumentum tenax. Conforto perspiciatis tum. Adsidue adfectus custodia. Qui recusandae patruus. Thorax vomer venio. Vere pecunia dolore. Possimus cupiditas consuasor. Ut crux thymum. Absum iste est. Officiis atque copia."),
						new Posts(5539, 500254, "Aliquid voluptatibus clementia crur turpis tunc.",
								"Vilis et minus. Asperiores triginta qui. Suscipit summopere curtus. Supplanto cupressus bellum. Corona demens arcus. Condico inventore blanditiis. Utilis cohaero vinum. Arcesso viriliter doloribus. Defero explicabo cuppedia. Aut veritatis cui. Aetas conitor cuius. Adiuvo vel bardus. Decens calcar acquiro. Patrocinor vos deficio. Termes laboriosam utrum. Decumbo voluptas considero. Correptius ut crur. Itaque succedo adsuesco."),
						new Posts(5543, 500250, "Aliquid voluptatibus clementia crur turpis tunc.",
								"Vilis et minus. Asperiores triginta qui. Suscipit summopere curtus. Supplanto cupressus bellum. Corona demens arcus. Condico inventore blanditiis. Utilis cohaero vinum. Arcesso viriliter doloribus. Defero explicabo cuppedia. Aut veritatis cui. Aetas conitor cuius. Adiuvo vel bardus. Decens calcar acquiro. Patrocinor vos deficio. Termes laboriosam utrum. Decumbo voluptas considero. Correptius ut crur. Itaque succedo adsuesco.")));
	}
}
