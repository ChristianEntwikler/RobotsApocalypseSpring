USE [robotsInversiondb]
GO
/****** Object:  Table [dbo].[survivor_infected_report]    Script Date: 23/03/2022 2:48:34 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[survivor_infected_report](
	[countid] [int] IDENTITY(1,1) NOT NULL,
	[survivorid] [varchar](50) NOT NULL,
	[dateuploaded] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[countid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[survivor_inventory_master]    Script Date: 23/03/2022 2:48:35 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[survivor_inventory_master](
	[itemid] [int] IDENTITY(1,1) NOT NULL,
	[itemname] [varchar](500) NOT NULL,
	[survivorid] [varchar](50) NOT NULL,
	[dateuploaded] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[itemid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[survivor_master]    Script Date: 23/03/2022 2:48:35 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[survivor_master](
	[tbid] [int] IDENTITY(1,1) NOT NULL,
	[id] [varchar](50) NOT NULL,
	[idtype] [varchar](50) NOT NULL,
	[fullname] [text] NOT NULL,
	[age] [varchar](5) NOT NULL,
	[gender] [varchar](20) NOT NULL,
	[locationlatitude] [varchar](50) NULL,
	[locationlongitude] [varchar](50) NULL,
	[infected] [nvarchar](5) NOT NULL,
	[datecreated] [datetime] NOT NULL,
	[datelocationupdated] [datetime] NULL,
	[date_infectionstatus_updated] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[tbid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[survivor_master] ADD  DEFAULT ('No') FOR [infected]
GO
ALTER TABLE [dbo].[survivor_master]  WITH CHECK ADD CHECK  (([infected]='Yes' OR [infected]='No'))
GO
CREATE UNIQUE INDEX ix_id
ON survivor_master(id);
GO
/****** Object:  StoredProcedure [dbo].[proc_update_infectionstatus]    Script Date: 23/03/2022 2:48:35 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
create   procedure [dbo].[proc_update_infectionstatus](
														@pid varchar(50),
														@pinfectedstatus varchar(5), 
														@pdateupdated datetime
														)

AS
BEGIN
	
if(@pinfectedstatus='Yes')
 begin
 insert into survivor_infected_report(survivorid, dateuploaded)values(@pid, @pdateupdated);
 
 if((select count(survivorid) from survivor_infected_report where survivorid = @pid) >= 3)
begin
update survivor_master set infected=@pinfectedstatus, date_infectionstatus_updated=@pdateupdated where id=@pid;
end;

 end;

END
GO
/****** Object:  StoredProcedure [dbo].[proc_update_location]    Script Date: 23/03/2022 2:48:35 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE   procedure [dbo].[proc_update_location](
												@plocationlatitude varchar(50), 
												@plocationlongitude varchar(50),  
												@pdatelocationupdated datetime,
												@pid varchar(50)
												)

AS
BEGIN
	update survivor_master set locationlatitude=@plocationlatitude, locationlongitude=@plocationlongitude, datelocationupdated=@pdatelocationupdated where id=@pid;
	return null
END
GO
