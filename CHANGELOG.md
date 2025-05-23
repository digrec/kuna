# Changelog

## [1.3.0](https://github.com/digrec/kuna/compare/v1.2.0...v1.3.0) (2025-05-07)


### Features

* add obverse and reverse images to Kuna model ([f20149c](https://github.com/digrec/kuna/commit/f20149cf3889f618211b5860aa5055a88758dd2c))
* load obverse Kuna image using Coil ([2cdf1e6](https://github.com/digrec/kuna/commit/2cdf1e6bd35ae83bef011d279d5e9cca8bf5e47c))
* persist fetched Kuna list and make it offline-first ([42c03c9](https://github.com/digrec/kuna/commit/42c03c95cb41a827649fa7724acb458a25d6f75c))
* target Android 15 (API 35) ([4eca016](https://github.com/digrec/kuna/commit/4eca0160da2438f3f1d7e196d4375565d321092d))


### Bug Fixes

* replace deprecated window inset API ([224c079](https://github.com/digrec/kuna/commit/224c07928c3a7eab6c7bfcb93806a533973a2f8e))

## [1.2.0](https://github.com/digrec/kuna/compare/v1.1.0...v1.2.0) (2023-09-30)


### Features

* load Kuna coins list from server ([739282c](https://github.com/digrec/kuna/commit/739282cdd952d3b7d0f601b59bf81ec683d28751))
* serializable KunaJson data model ([db43ac7](https://github.com/digrec/kuna/commit/db43ac7ebd5da8429b5b9aaa9485cfd1b0d70b81))
* show list loading indicator or error in respective UI state ([7b76dc1](https://github.com/digrec/kuna/commit/7b76dc10491a8cb840b10fc639e82dcde4d960b6))


### Bug Fixes

* **readme:** lower the app screenshot size ([798c923](https://github.com/digrec/kuna/commit/798c923fbcd496dc89189b164ccc34071c425583))
* set current Koin instance to Compose context ([211a23e](https://github.com/digrec/kuna/commit/211a23e752a849cee65a96a7b2bb51c73fb9c731))


### Documentation

* add app screenshot to README.md ([19c1a8e](https://github.com/digrec/kuna/commit/19c1a8ef0015864cca9a68419b94152d1f9269b0))


### Styles

* fix interface formatting ([64a4aa5](https://github.com/digrec/kuna/commit/64a4aa5ecb64de3158f0fef05dab7bcb0204f795))
* make sealed sub-object a data object ([c6c382c](https://github.com/digrec/kuna/commit/c6c382cfdaacde0e96ac3fc919392c98c683e445))


### Chores

* **deps:** update AGP to v8.1.1 ([23576c5](https://github.com/digrec/kuna/commit/23576c56fb7265218301aa70aae6ec1745b8afdf))
* **deps:** update AGP to v8.1.2 ([80e1cdf](https://github.com/digrec/kuna/commit/80e1cdf4241d9a37dde35619210ac43d75c4e7d0))
* **deps:** update AndroidX to v1.12 ([a9c98ee](https://github.com/digrec/kuna/commit/a9c98ee62ed017b14274cbe642a34c703fadb063))
* **deps:** update Compose to v2023.08 ([0701427](https://github.com/digrec/kuna/commit/0701427c1175654b661a471001a1f22b247f2d07))
* **deps:** update Compose to v2023.09.02 ([5bad7f1](https://github.com/digrec/kuna/commit/5bad7f167ddea43c1db8a57d8ab76194b845ce3d))
* **deps:** update Koin to v3.4.3 and Koin Compose to v3.4.6 ([db64016](https://github.com/digrec/kuna/commit/db640162e0471947683dccbb180f782a94cd5dd6))
* **deps:** update Koin to v3.5.0 and Koin Compose to v3.5.0 ([d0bb5a4](https://github.com/digrec/kuna/commit/d0bb5a46cbfc467a8a9aa8473bd0684a04d1c249))
* **deps:** update Kotlin to v1.9 ([0c9de15](https://github.com/digrec/kuna/commit/0c9de158b325ccceae78134f2653a7a3a2ef927b))
* **deps:** update Navigation to v2.7.1 ([88f572f](https://github.com/digrec/kuna/commit/88f572f8eabec93f0115d74792650264c2d033e0))
* replace deprecated packagingOptions with packaging ([7f462c0](https://github.com/digrec/kuna/commit/7f462c07a1bd830ecd58e38f07c259c8882f3d06))


### Refactors

* use kotlinx.datetime instead of java.time ([f7cabbe](https://github.com/digrec/kuna/commit/f7cabbec74b6e5ffee7bb1ad3b06d8465a1a0b52))


### Build System

* add "-debug" suffix to app version name on debug build ([08adcc5](https://github.com/digrec/kuna/commit/08adcc5b1073f90d27df87b8dcac5fc3a698286a))
* target Android 14 (API 34) ([2309d5e](https://github.com/digrec/kuna/commit/2309d5e8232925f2941ef317c8a1a3fb86a5815f))

## [1.1.0](https://github.com/digrec/kuna/compare/v1.0.0...v1.1.0) (2023-07-03)


### Features

* add full title to Kuna entity ([2a0da85](https://github.com/digrec/kuna/commit/2a0da853b93b7725ff504d45c485b92b5b747ba5))
* show 3 line title and a placeholder icon on Kuna card ([df87718](https://github.com/digrec/kuna/commit/df87718dce5b6dcea099f69ff7b2e2b8d5d2446e))
* show collected checkmark on Kuna card ([2675de3](https://github.com/digrec/kuna/commit/2675de356e607386bde3272a4837cf8c78a24ef4))


### Bug Fixes

* **ci:** fix build.gradle.kts file path in release-please.yml ([93c2ce0](https://github.com/digrec/kuna/commit/93c2ce098d520cf25815dbe284b3e82e78de672c))
* **ci:** list build.gradle.kts for updates by Release Please ([4ab1c29](https://github.com/digrec/kuna/commit/4ab1c297d16c5dbe6daae449bd4294263919ff4b))


### Chores

* create project LICENSE ([51e67ae](https://github.com/digrec/kuna/commit/51e67ae12609d4e3dc4cca0d3eae36eb00f5d20f))
* **deps:** update Activity to v1.7.2 ([be4b15c](https://github.com/digrec/kuna/commit/be4b15ccee3605c3dd24144ae9a0b9394bb236df))
* **deps:** update AGP to v8.0.2 ([5aad320](https://github.com/digrec/kuna/commit/5aad32067dcdae0986b4d9bb437f2ebab5b72380))
* **deps:** update AGP to v8.1.0-rc01 ([28b2613](https://github.com/digrec/kuna/commit/28b2613bad81b89f1f68de58cbb1c2330496868e))
* **deps:** update AndroidX to v1.10.1 ([e40f720](https://github.com/digrec/kuna/commit/e40f720c3fbfd2f5fd9856ef697ad4c887627eec))
* **deps:** update Compose to v2023.05.01 ([63ed903](https://github.com/digrec/kuna/commit/63ed903cf739bdf3a51f4032fd2a93d6d9977eea))
* **deps:** update Compose to v2023.06.01 ([ccd6027](https://github.com/digrec/kuna/commit/ccd60271c3008c592eba0cb05e52827bab005d21))
* **deps:** update Koin to v3.4.2 and Koin Compose to v3.4.5 ([43385d7](https://github.com/digrec/kuna/commit/43385d72bd6865ebb2c3a72460de66f40ad90c1b))
* **deps:** update Kotlin to v1.8.21 ([f1b1c18](https://github.com/digrec/kuna/commit/f1b1c18a50db6a91f7c244101b76943a6d01aadd))
* **deps:** update Navigation to v2.6 ([3d23254](https://github.com/digrec/kuna/commit/3d23254405e0bd38f68a8bad14ed98ba35f8b460))

## 1.0.0 (2023-05-11)


### Features

* **build:** update Gradle to v7.4 and AGP to v7.3 ([c06eb37](https://github.com/digrec/kuna/commit/c06eb37e09696d68c762a72996ed477ad578a10e))
* create initial Kuna project ([aa5aeec](https://github.com/digrec/kuna/commit/aa5aeec8a151bdadce4ad149fff34ead57a4603a))
* **docs:** show version badge in README.md ([dec276c](https://github.com/digrec/kuna/commit/dec276cc30cf76708f8a38149f585b3dd289b139))
* **docs:** show version badge in README.md ([8fa7ba3](https://github.com/digrec/kuna/commit/8fa7ba3dcd4942bbaa089fae1f61ef830c7798f8))
* implement initial Kuna list screen and app settings screen ([08ed6de](https://github.com/digrec/kuna/commit/08ed6de9ae144e1b9fee9ae341203e7865fb7a3e))
* implement Kuna list screen with repository and use case ([e38a55a](https://github.com/digrec/kuna/commit/e38a55ae7e793092ab22979b0b0bfb84371ba6c1))
* implement main KunaApp component with app bar ([059b637](https://github.com/digrec/kuna/commit/059b63793132e48b61620bbbf01360fd03833396))
* show coin's date of release ([ed551e1](https://github.com/digrec/kuna/commit/ed551e1325b3e6a667b03a49d3807d4437ba08f2))
* show number of items issued per coin ([6b4c5db](https://github.com/digrec/kuna/commit/6b4c5dbf7529410a936d8fa6efa90469b88c9d05))
* show remaining (total 19) 25 kuna coins ([20b8141](https://github.com/digrec/kuna/commit/20b814126dab974673a43932028d75f61fc77951))
* update Activity to v1.6.1 ([b49d1ae](https://github.com/digrec/kuna/commit/b49d1aebb50e5f5c3f76c49e0a089e451f667991))
* update AndroidX to v1.9 ([3a0856b](https://github.com/digrec/kuna/commit/3a0856bd617af24e5c4dbca3632173d967f17ae8))
* update app version code and name using Release Please ([08e61c5](https://github.com/digrec/kuna/commit/08e61c5931a6d796a24466af7da906c090446b22))
* update Compose to v2022.11, Espresso to v3.5 and JUnitExt to v1.1.4 ([6f6289d](https://github.com/digrec/kuna/commit/6f6289d2cc33b248ec477418fd49baeb4c3321b8))
* update Compose to v2022.12 ([98c3a54](https://github.com/digrec/kuna/commit/98c3a542bc1f8bd0a90262bacb2f6809fb6ec8db))
* update Compose to v2023.01 and Compose Compiler to v1.4 ([df4fa17](https://github.com/digrec/kuna/commit/df4fa1754ffeda0f5976fe446da1c2ff501c9616))
* update Gradle to v7.5 and AGP to v7.4 ([8f90010](https://github.com/digrec/kuna/commit/8f900105373863e174a60263f7ceb92013188d35))
* update Koin to v3.3 ([1a692cb](https://github.com/digrec/kuna/commit/1a692cb1b5541b61beb0e5ecc0e284f58a6e800b))
* update Koin to v3.3.2 and Koin Compose to v3.4.1 ([238bb0b](https://github.com/digrec/kuna/commit/238bb0b241cedb7d4baaef0b866bf1200bb945d6))
* update Kotlin to v1.8 ([1fae46c](https://github.com/digrec/kuna/commit/1fae46c9e906b51bdd985e3e270f519e04621a63))


### Bug Fixes

* show real app version ([88c536c](https://github.com/digrec/kuna/commit/88c536c41aeceaf275cd915556b4824ff3200338))
* update AGP to v7.3.1 ([c74155f](https://github.com/digrec/kuna/commit/c74155f42611427efff1e17672ac64b778a0c093))
* update Compose BOM to stable v2022.10.00 ([497295e](https://github.com/digrec/kuna/commit/497295e0b6de67fd2440b9d254f26f2616cdd8b8))
* update Kotlin to v1.7.20 ([43f19ba](https://github.com/digrec/kuna/commit/43f19ba35d1ee74bf4baa51a84fa6fff5e8b1a22))


### Refactors

* **build:** migrate manifest package to android namespace ([917d4bd](https://github.com/digrec/kuna/commit/917d4bd9d34c792e52403dc2dbff9c74642b9b1f))
* move Kuna list screen from core to feature package ([cf9f26c](https://github.com/digrec/kuna/commit/cf9f26ce12b2d9706a186c9896f44b6987e1ae3e))
* move Kuna list view classes to ui subpackage ([5a3ae4b](https://github.com/digrec/kuna/commit/5a3ae4b7db8d899f089cf4e009e12dccb46ff1e3))
* move KunaCard from ui to feature package ([30d6c1b](https://github.com/digrec/kuna/commit/30d6c1b87d609d022a1efb175c721587d3fe6295))
* move MainActivity into ui package ([862caa6](https://github.com/digrec/kuna/commit/862caa675cfca5105585f11b9fb1f892573acd22))
* move Settings screen from core to feature package ([38673c6](https://github.com/digrec/kuna/commit/38673c662678a18bd67905dbfec4658a3d3eb918))
* move Settings view classes to ui subpackage ([f2ff6d4](https://github.com/digrec/kuna/commit/f2ff6d43c92abcd39230bbbbb01ee0ac335dd94a))
* move Theme from ui to core.ui package ([5bd08ee](https://github.com/digrec/kuna/commit/5bd08ee68c1bfea05ee14737a4ea02c167a128a6))
* use lowercase package name ([0bbb3ef](https://github.com/digrec/kuna/commit/0bbb3ef728336fd5f98d34b6c835beeff2db8570))


### Styles

* order dependency versions alphabetically ([a0439b3](https://github.com/digrec/kuna/commit/a0439b3279b4cbf06e97bc655955e3128618468b))
* use single name imports ([5fe4e90](https://github.com/digrec/kuna/commit/5fe4e909d100e114a5d16e67b4504edb7936e5ed))


### Build System

* enable JDK desugaring to allow java.time.LocalDate usage ([8395ad6](https://github.com/digrec/kuna/commit/8395ad6b56e2e6bb20be74f81ab100794be0fee3))
* sign the release build ([08e6444](https://github.com/digrec/kuna/commit/08e6444387c233f744cb02c559a0376cd0c73764))
* target JVM 11 bytecode ([fa66a4a](https://github.com/digrec/kuna/commit/fa66a4a9f3caaf721fcbabd43104c42cd8e0f920))
* update AGP to v7.4.2 ([2228c14](https://github.com/digrec/kuna/commit/2228c146cd961c4ada8d08c0d7f6ee9d98af5faf))
* upgrade Gradle to v8.0 and AGP to v8.0 ([440a0c9](https://github.com/digrec/kuna/commit/440a0c927cb88c3ceac76a115513f454196b31bc))


### Chores

* add Koin for dependency injection ([738d37d](https://github.com/digrec/kuna/commit/738d37d4335d86079f625cb3705a78bfee82d3a1))
* add Timber logger and plant a debug tree ([4da0e1b](https://github.com/digrec/kuna/commit/4da0e1b866010cd5de656f89edf36364c27abce9))
* cite the data source in the README.md ([40c1706](https://github.com/digrec/kuna/commit/40c17065c3b57f4d368f6f0863450fd64e2d4199))
* **deps:** update Activity to v1.7.1 ([c106fcd](https://github.com/digrec/kuna/commit/c106fcd86e2eade28476116aae3a841ed7710edc))
* **deps:** update AGP to v7.4.1 ([af5eb8f](https://github.com/digrec/kuna/commit/af5eb8f5f28a07343dd717e02ec54e44ff8bdf60))
* **deps:** update AGP to v8.0.1 ([b47aa3f](https://github.com/digrec/kuna/commit/b47aa3f4df15b361d8c275fefb708261a1498ecf))
* **deps:** update AndroidX to v1.10, Compose to v2023.04.00, Koin to v3.4 ([3f2f32c](https://github.com/digrec/kuna/commit/3f2f32c384a3733966bb6486d4d01a1b71e6edd2))
* **deps:** update Compose Compiler to v1.4.6 ([a5c9fea](https://github.com/digrec/kuna/commit/a5c9feac5fdcde9e1838e9d54670cefc1f79f785))
* **deps:** update Compose to v2023.04.01 ([49a094f](https://github.com/digrec/kuna/commit/49a094fbae013a6a927ca176f28a031e6e114c38))
* **deps:** update Koin Compose to v3.4.4 ([0fdfc45](https://github.com/digrec/kuna/commit/0fdfc457c340965d5b48064f4b9abcb321883193))
* **deps:** update Koin to v3.3.3 and Koin Compose to v3.4.2 ([d09467c](https://github.com/digrec/kuna/commit/d09467cce240bc2ff72c18c215c597bca3c32406))
* **deps:** update Kotlin to v1.8.20 ([3a14754](https://github.com/digrec/kuna/commit/3a1475422c0970f12eed4a7c3a044783c4ce9514))
* **deps:** update Lifecycle to v2.6.0 ([a5bfa6c](https://github.com/digrec/kuna/commit/a5bfa6c2d3e70d7bc5c7d85bae483d0554607af8))
* **deps:** update Lifecycle to v2.6.0-beta01 ([1d061ea](https://github.com/digrec/kuna/commit/1d061ead131e686043dd1e1abc41e4b3605ac384))
* **deps:** use Lifecycle v2.6.0-alpha05 as common androidx.lifecycle version ([12d5173](https://github.com/digrec/kuna/commit/12d51730e572d639e94fac1b64e5484e65863e02))


### Continuous Integration

* add Release Please workflow to author Release PRs ([15c96f1](https://github.com/digrec/kuna/commit/15c96f1fa1cd8a7c99180481a501987904419e3d))
