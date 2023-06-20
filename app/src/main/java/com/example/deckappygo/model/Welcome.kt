package com.example.deckappygo.model

import com.google.gson.annotations.SerializedName

//data class CartaModel(
//
//    //EndPoints
//
//    //Dentro del paquete data:
//    val id: Int,
//    val name: String,
//    val type: String,
//    val frameType: String,
//    val desc: String,
//    val atk: Int,
//    val def: Int,
//    val level: Int,
//    val race: String,
//    val attribute: String,
//    val archetype: String,
//    val scale: String,
//    val linkval: String,
//    val linkmarkers: String,
//
//    //Dentro del paquete card_images:
//    //val id: Int
//    val image_url: ArrayList<String>,
//    val image_url_small: ArrayList<String>,
//    val image_url_cropped: ArrayList<String>
//
//
//)
data class Welcome (
    val id: Long,
    val name: String,
    val type: Type,
    val frameType: FrameType,
    val desc: String,
    val race: Race,
    val archetype: String? = null,
    val cardSets: List<CardSet>? = null,
    val cardImages: List<CardImage>,
    val cardPrices: List<CardPrice>,
    val atk: Long? = null,
    val def: Long? = null,
    val level: Long? = null,
    val attribute: Attribute? = null,
    val scale: Long? = null,
    val linkval: Long? = null,
    val linkmarkers: List<Linkmarker>? = null,
    val banlistInfo: BanlistInfo? = null
)

data class Datum (
    val id: Long,
    val name: String,
    val type: Type,
    val frameType: FrameType,
    val desc: String,
    val race: Race,
    val archetype: String? = null,
    val cardSets: List<CardSet>? = null,
    val cardImages: List<CardImage>,
    val cardPrices: List<CardPrice>,
    val atk: Long? = null,
    val def: Long? = null,
    val level: Long? = null,
    val attribute: Attribute? = null,
    val scale: Long? = null,
    val linkval: Long? = null,
    val linkmarkers: List<Linkmarker>? = null,
    val banlistInfo: BanlistInfo? = null
)

enum class Attribute {
    Dark,
    Divine,
    Earth,
    Fire,
    Light,
    Water,
    Wind
}

data class BanlistInfo (
    val banGoat: Ban? = null,
    val banTcg: Ban? = null,
    val banOcg: Ban? = null
)

enum class Ban {
    Banned,
    Limited,
    SemiLimited
}

data class CardImage (
    val id: Long,
    val imageURL: String,
    val imageURLSmall: String,
    val imageURLCropped: String
)

data class CardPrice (
    val cardmarketPrice: String,
    val tcgplayerPrice: String,
    val ebayPrice: String,
    val amazonPrice: String,
    val coolstuffincPrice: String
)

data class CardSet (
    val setName: String,
    val setCode: String,
    val setRarity: SetRarity,
    val setRarityCode: SetRarityCode,
    val setPrice: String
)

enum class SetRarity {
    C,
    CollectorSRare,
    Common,
    DuelTerminalNormalParallelRare,
    DuelTerminalNormalRareParallelRare,
    DuelTerminalRareParallelRare,
    DuelTerminalSuperParallelRare,
    DuelTerminalUltraParallelRare,
    ExtraSecret,
    ExtraSecretRare,
    GhostGoldRare,
    GhostRare,
    GoldRare,
    GoldSecretRare,
    MosaicRare,
    NormalParallelRare,
    PlatinumRare,
    PlatinumSecretRare,
    PremiumGoldRare,
    PrismaticSecretRare,
    QCScR,
    QuarterCenturySecretRare,
    Rare,
    SecretRare,
    ShatterfoilRare,
    ShortPrint,
    Starfoil,
    StarfoilRare,
    StarlightRare,
    SuperParallelRare,
    SuperRare,
    SuperShortPrint,
    The10000SecretRare,
    UltimateRare,
    UltraParallelRare,
    UltraRare,
    UltraRarePharaohSRare,
    UltraSecretRare
}

enum class SetRarityCode {
    C,
    CR,
    Dnpr,
    Drpr,
    Dspr,
    Dupr,
    Empty,
    GScR,
    Ggr,
    Gr,
    Gur,
    Msr,
    PG,
    PS,
    PScR,
    Pir,
    R,
    SP,
    SSP,
    ScR,
    Sfr,
    Shr,
    Spr,
    Sr,
    StR,
    The10000ScR,
    UScR,
    Upr,
    Ur,
    UtR
}

enum class FrameType {
    Effect,
    EffectPendulum,
    Fusion,
    FusionPendulum,
    Link,
    Normal,
    NormalPendulum,
    Ritual,
    RitualPendulum,
    Skill,
    Spell,
    Synchro,
    SynchroPendulum,
    Token,
    Trap,
    Xyz,
    XyzPendulum
}

enum class Linkmarker {
    Bottom,
    BottomLeft,
    BottomRight,
    Left,
    Right,
    Top,
    TopLeft,
    TopRight
}

enum class Race {
    AbidosTheTh,
    AdrianGecko,
    AlexisRhodes,
    Amnael,
    Andrew,
    Aqua,
    Arkana,
    AsterPhoenix,
    AxelBrodie,
    BastionMisaw,
    Beast,
    BeastWarrior,
    Bonz,
    Camula,
    ChazzPrincet,
    Christine,
    ChumleyHuffi,
    Continuous,
    Counter,
    CreatorGod,
    Cyberse,
    DRVellianC,
    David,
    Dinosaur,
    DivineBeast,
    DonZaloog,
    Dragon,
    Emma,
    Empty,
    Equip,
    EspaRoba,
    Fairy,
    Field,
    Fiend,
    Fish,
    Illusionist,
    Insect,
    Ishizu,
    IshizuIshtar,
    JadenYuki,
    JesseAnderso,
    Joey,
    JoeyWheeler,
    Kagemaru,
    Kaiba,
    Keith,
    LumisAndUMB,
    LumisUmbra,
    Machine,
    Mai,
    MaiValentine,
    Mako,
    Nightshroud,
    Normal,
    Odion,
    ParadoxBroth,
    Pegasus,
    Plant,
    Psychic,
    Pyro,
    QuickPlay,
    Reptile,
    Rex,
    Ritual,
    Rock,
    SeaSerpent,
    SetoKaiba,
    Spellcaster,
    SyrusTruesda,
    Tania,
    TeaGardner,
    TheSupremeK,
    TheloniousVi,
    Thunder,
    Titan,
    TyrannoHassl,
    Warrior,
    Weevil,
    WingedBeast,
    Wyrm,
    YamiBakura,
    YamiMarik,
    YamiYugi,
    Yubel,
    Yugi,
    ZaneTruesdal,
    Zombie
}

enum class Type {
    EffectMonster,
    FlipEffectMonster,
    FusionMonster,
    GeminiMonster,
    LinkMonster,
    NormalMonster,
    NormalTunerMonster,
    PendulumEffectFusionMonster,
    PendulumEffectMonster,
    PendulumEffectRitualMonster,
    PendulumFlipEffectMonster,
    PendulumNormalMonster,
    PendulumTunerEffectMonster,
    RitualEffectMonster,
    RitualMonster,
    SkillCard,
    SpellCard,
    SpiritMonster,
    SynchroMonster,
    SynchroPendulumEffectMonster,
    SynchroTunerMonster,
    Token,
    ToonMonster,
    TrapCard,
    TunerMonster,
    UnionEffectMonster,
    XYZMonster,
    XYZPendulumEffectMonster
}
