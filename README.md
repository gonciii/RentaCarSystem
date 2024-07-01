Araç Kiralama Uygulaması


Bu proje, Java ve Swing teknolojileri kullanılarak geliştirilecek olan bir araç kiralama uygulamasını içermektedir.Aracın tüm yaşam döngüsünü takip eden ve rezervasyon işlemlerini yöneten bir uygulamadır.

-----> Teknolojiler

Java: Nesne yönelimli programlama prensiplerini temel alan, geniş bir kullanıcı tabanına sahip bir programlama dilidir.

Swing: Java programlama dilinde GUI bileşenleri oluşturmak ve yönetmek için kullanılan bir kütüphanedir. Geniş bir bileşen seti sunar ve masaüstü uygulamaları için idealdir.

PostgreSQL: İlişkisel veritabanı yönetim sistemi (RDBMS) olarak kullanılacaktır. PostgreSQL, güçlü SQL sorgu desteği ve geniş özellik setiyle bilinir.


---- > Proje Yapısı

Proje katmanlı mimari ile oluşturulmuştur.Kullanılan modüller :

Entity Modülü

Veritabanı tablolarını ve bu tablolara ait varlık nesnelerini tanımlar. Kullanıcı, araç, rezervasyon gibi temel entity nesnelerini içerir ve bu nesneler arasındaki ilişkileri belirler.

DAO Modülü (Veri Erişim Nesnesi)

Veritabanı erişimi ve işlemleri için arayüz sağlar. Entity modülündeki nesnelerin PostgreSQL veritabanına kaydedilmesi, güncellenmesi, silinmesi işlemlerini yönetir ve veritabanından veri alma işlemlerini gerçekleştirir.

Business Modülü

Uygulama içindeki temel işlemleri ve iş mantığını yönetir. Fiyatlandırma, araç kiralama hesaplamaları gibi iş mantığı operasyonlarını dao modülü ile etkileşim içinde gerçekleştirir.

View Modülü

Kullanıcı arayüzünü (UI) yönetir ve kullanıcıyla etkileşimi sağlar. Araç listesi, kiralama ekranı gibi kullanıcı bilgilerini gösterir ve kullanıcının girdiği bilgileri iş katmanına ileterek işlemleri başlatır.

![Ekran Görüntüsü (47)](https://github.com/gonciii/RentaCarSystem/assets/114026990/96bece7a-ffa3-42e9-ae63-e7049e03564c)

![Ekran Görüntüsü (50)](https://github.com/gonciii/RentaCarSystem/assets/114026990/b689d710-ebff-4a72-beb4-253bc7717d06)


