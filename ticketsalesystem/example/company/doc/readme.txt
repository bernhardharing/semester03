class-reference.chm
    cannot be viewed when located on a network drive
    -> must be copied to local hard disk !

Examples

  Intro

    Some of the Examples originate from the Book

    Pro_JPA_2_Mastering_the_Java_Persistence_API.pdf.

    further abbreviated as [JPA2]


    More information about the examples can be found in

        [JPA2] Preface p xxv : "Code Examples"


  Overview (from [JPA2])

    Example Name                    Origimal Name in  [JPA2]
    --------------------------------------------------------

    simplest                        Chapter2
    enumeration                     Chapter4\07-enumMapping
    sequence                        Chapter4\12-sequenceIdGeneration
    oneToOne                        Chapter4\17-oneToOneBidirectional
    manyToOne                       Chapter4\19-oneToManyBidirectional
    manyToMany                      Chapter4\21-manyToManyBidirectional

    queries                         Chapter7\02-namedQueryExample
                                &&  Chapter8\jpqlExamples
  Example Notes

    manyToManyAttributed

        A Junction Entity is needed; hovever, we hide the Junction Entity
        from the user.
        This is accomplished as follows :

        * Project and Employee get a new (transient) attribute : time_percent

        * Employee gets a new method : getProjects()
          This method converts the EmpProjJunction List into
          a Project list, filling the Project's time_percent field.

        * Project gets a new method : getEmployees()
          This method converts the EmpProjJunction List into
          an Employee list, filling the Employee's time_percent field.


    Security
        This example is based on the company\queries example

        That means that first you have to run all the SQL scripts in

            ...\company\sql\queries

   inheritanceIndivudualPKs
        Given
            one parent class, two child classes

        customer request

            child classes shall have their  o w n  r a n g e  for their PK.

            PK's for FullTimeEmployee shall use FullTimeEmployee_Sequence
            PK's for PartTimeEmployee shall use PartTimeEmployee_Sequence

