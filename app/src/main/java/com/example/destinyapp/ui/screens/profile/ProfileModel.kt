package com.example.destinyapp.ui.screens.profile

data class ProfileState(
    val name: String = "Javier Sanchez",
    val email: String = "javier@destiny.com",
    val birthDate: String = "1995-05-15",
    val gender: String = "Masculino",
    val selectedTags: Set<String> = emptySet(),
    val isEditing: Boolean = false,
    val isLoading: Boolean = false
)

val INTEREST_TAGS = mapOf(
    "Música y Festivales (Auditivo)" to listOf(
        "Techno & House", "Reggaetón Old School", "Indie Rock Local", 
        "Jazz & Wine Nights", "Festivales Masivos", "Trap & Hip-Hop", 
        "Acústicos en Vivo", "Tributos y Covers", "EDM", "K-Pop Random Dance"
    ),
    "Vida Nocturna y Social (Networking)" to listOf(
        "Rooftops", "Mixología de Autor", "Open Bar", "Speakeasy", 
        "Fiestas de Disfraces", "After-Party", "VIP Access", "Karaoke Night", 
        "Antros & Clubs", "Ladies Night"
    ),
    "Deportes y Bienestar (Activo)" to listOf(
        "Retas de Volleyball", "Boxeo Mexicano", "Yoga al Aire Libre", 
        "Torneos de Pádel", "Senderismo (Hiking)", "Running Crew", 
        "Crossfit Games Local", "Ciclismo Urbano", "Maratones y 5K"
    ),
    "Arte, Cultura y Hobby (Experiencias)" to listOf(
        "Stand-up Comedy", "Galerías de Arte", "Bazares de Diseño", 
        "Teatro Independiente", "Cine al Aire Libre", "Talleres de Pintura y Vino", 
        "Poesía Slam", "Fotografía Urbana", "Danza Contemporánea"
    ),
    "Tech, Gaming y Geek (Conexión)" to listOf(
        "Hackathons", "Torneos de E-sports", "Networking Tech", 
        "Lanzamientos de Software", "Cosplay & Anime", "Juegos de Mesa", "Cisco & Networking"
    ),
    "Gastronomía (Foodie)" to listOf(
        "Brunch & Chill", "Catas de Vino", "Street Food Gourmet", 
        "Cenas Clandestinas", "Coffee Lovers", "Festivales de la Cerveza", "Eventos Veganos"
    )
)
