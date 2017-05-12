package natekfl.admissionsBot.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import de.btobastian.javacord.listener.message.MessageEditListener;
import de.btobastian.javacord.listener.server.ServerMemberAddListener;

public class AdmissionsBot {

	static int httpTimeout = 0;

	public static void main(String[] args) throws IOException {

		final String LabourLottery = "290904284353003521";
		final String Apprentice = "306756265210544130";
		final String Junior = "290904560313303041";
		final String Inspector = "290731255346102274";
		final String Senior = "306748572273999873";
		final String Head = "306748576921550849";
		final String JS = "306748567253417984";
		final String Supervisor = "306748575302287361";
		final String Chief = "306748573452599320";
		final String Pending = "290928540743106560";
		final String Trainer = "307278407203553280";

		DiscordAPI api = Javacord.getApi("Mjk2NzMxNjg3NTc1MDkzMjU3.C8qi4g.YGLbL09I3b_eJh5XTPnmFKnNlVk", true);
		api.connectBlocking();

		api.setGame("?rankme");
		
		
		api.registerListener(new MessageEditListener() {
			public void  onMessageEdit(DiscordAPI api, Message message, String oldContent) {
				
				if (message.getContent().toLowerCase().replace(" ", "").contains("kach") || message.getContent().toLowerCase().replace(" ", "").contains("aakk")) {

					if (message.getAuthor().getId().equals("277528693423734784") == false) {

						message.delete();

					}

				}
				
			}
		});

		api.registerListener(new ServerMemberAddListener() {
			public void onServerMemberAdd(DiscordAPI api, User user, Server server) {

				server.getChannelById("290906464862601216").sendMessage("Welcome " + user.getMentionTag()
						+ " to the Federal Admissions discord. If you are in the group, please do the command ?rankme");
				server.getRoleById(Pending).addUser(user);

			}
		});

		api.registerListener(new MessageCreateListener() {
			public void onMessageCreate(DiscordAPI api, Message message) {

				String userID = null;
				String user = null;
				String userRank = null;

				Server server = message.getChannelReceiver().getServer();

				if (message.getContent().toLowerCase().replace(" ", "").contains("kach") || message.getContent().toLowerCase().replace(" ", "").contains("aakk")) {

					if (message.getAuthor().getId().equals("277528693423734784") == false) {

						message.delete();

					}

				}

				if (message.getContent().equalsIgnoreCase("?totalpoints")
						|| message.getContent().equalsIgnoreCase("?totalpointzzz")) {

					String jsonSheet = null;

					String[][] leaderboard = new String[2][1000];

					URL url;
					try {
						url = new URL(
								"https://sheets.googleapis.com/v4/spreadsheets/1_YpHDEn_cFrqhhd3vh_RRdoGTuGRtPDftIWKxOfvwVU/values/'Form%20Responses%201'!C:C?key=AIzaSyAfGewvwwL03s2DVgft5TkzuGLL8qhyY2Y");
						BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

						String inputLine;
						while ((inputLine = in.readLine()) != null)
							jsonSheet += inputLine;
						in.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

					jsonSheet = (jsonSheet.toString().replaceAll("null", ""));

					JSONObject obj = new JSONObject(jsonSheet);

					JSONArray arr = obj.getJSONArray("values");

					String jsonSheet1 = null;

					URL url1;
					try {
						url1 = new URL(
								"https://sheets.googleapis.com/v4/spreadsheets/1_YpHDEn_cFrqhhd3vh_RRdoGTuGRtPDftIWKxOfvwVU/values/'Form%20Responses%201'!J:J?key=AIzaSyAfGewvwwL03s2DVgft5TkzuGLL8qhyY2Y");
						BufferedReader in = new BufferedReader(new InputStreamReader(url1.openStream()));

						String inputLine;
						while ((inputLine = in.readLine()) != null)
							jsonSheet1 += inputLine;
						in.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

					jsonSheet1 = (jsonSheet1.toString().replaceAll("null", ""));

					JSONObject obj1 = new JSONObject(jsonSheet1);

					JSONArray arr1 = obj1.getJSONArray("values");

					List<String> alreadyChecked = new ArrayList<String>();

					for (int i = 1; i < arr.length(); i++) {

						List<String> list = Arrays.asList(((arr.get(i).toString().replace("[", "").replace("]", "")
								.replace("\"", "").split("\\s*,\\s*"))));

						List<String> list1 = Arrays.asList(((arr1.get(i).toString().replace("[", "").replace("]", "")
								.replace("\"", "").split("\\s*,\\s*"))));

						if ((alreadyChecked.contains(list.get(0).toString().toLowerCase()) == false)) {

							for (int e = 0; e < leaderboard[0].length; e++) {

								if (leaderboard[0][e] == null) {

									leaderboard[0][e] = list.get(0);
									leaderboard[1][e] = list1.get(0);
									alreadyChecked.add(list.get(0).toLowerCase());
									break;

								}

							}

						} else {

							for (int c = 0; c < leaderboard[0].length; c++) {

								if (leaderboard[0][c].toLowerCase().contains(list.get(0).toLowerCase())) {

									leaderboard[1][c] = Integer.toString(
											Integer.parseInt(leaderboard[1][c]) + Integer.parseInt(list1.get(0)));
									break;

								}

							}

						}

					}

					int LeaderLength = 0;

					for (int a = 0; a < leaderboard[0].length; a++) {

						if (leaderboard[0][a] != null) {

							LeaderLength++;

						} else {

							break;

						}

					}

					ArrayList<ArrayList<String>> leaderboardFinal = new ArrayList<ArrayList<String>>();

					for (int i = 0; i < leaderboard[0].length; i++) {

						if (i < LeaderLength) {

							leaderboardFinal.add(new ArrayList<String>(
									Arrays.asList(leaderboard[0][i].toString(), leaderboard[1][i].toString())));
						} else {

							break;

						}

					}

					String messageToSend = "";

					for (int f = 0; f < leaderboardFinal.size(); f++) {

						messageToSend += ("**" + leaderboardFinal.get(f).get(0) + "** | **"
								+ leaderboardFinal.get(f).get(1) + "**\n");

						if (messageToSend.length() >= 1950) {

							message.reply(messageToSend);
							messageToSend = "";

						}

					}

					message.reply(messageToSend);

				}

				if (message.getContent().equalsIgnoreCase("?points")
						|| message.getContent().equalsIgnoreCase("?pointzzz")) {

					String checkVerify;
					try {
						checkVerify = checkVerification(message.getAuthor().getId());
					} catch (IOException e) {
						e.printStackTrace();
						checkVerify = ("Error: " + e.toString());
					}

					if (checkVerify.equals("1")) {

						message.reply("Too many requests. Try again later.");

					} else if (checkVerify.contains("Error:")) {

						message.reply("An unknown error occurred.\n\n" + checkVerify);

					} else if (checkVerify.equals("0")) {

						message.reply(
								"You are not verified. Please visit https://verify.eryn.io/ to verify. You can do ?checkverify to make sure the verification went through.");

					} else {

						user = checkVerify;

						String jsonSheet = null;

						URL url;
						try {
							url = new URL(
									"https://sheets.googleapis.com/v4/spreadsheets/1_YpHDEn_cFrqhhd3vh_RRdoGTuGRtPDftIWKxOfvwVU/values/'Form%20Responses%201'!C:C?key=AIzaSyAfGewvwwL03s2DVgft5TkzuGLL8qhyY2Y");
							BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

							String inputLine;
							while ((inputLine = in.readLine()) != null)
								jsonSheet += inputLine;
							in.close();
						} catch (Exception e) {
							e.printStackTrace();
						}

						jsonSheet = (jsonSheet.toString().replaceAll("null", ""));

						JSONObject obj = new JSONObject(jsonSheet);

						JSONArray arr = obj.getJSONArray("values");

						String jsonSheet1 = null;

						URL url1;
						try {
							url1 = new URL(
									"https://sheets.googleapis.com/v4/spreadsheets/1_YpHDEn_cFrqhhd3vh_RRdoGTuGRtPDftIWKxOfvwVU/values/'Form%20Responses%201'!J:J?key=AIzaSyAfGewvwwL03s2DVgft5TkzuGLL8qhyY2Y");
							BufferedReader in = new BufferedReader(new InputStreamReader(url1.openStream()));

							String inputLine;
							while ((inputLine = in.readLine()) != null)
								jsonSheet1 += inputLine;
							in.close();
						} catch (Exception e) {
							e.printStackTrace();
						}

						jsonSheet1 = (jsonSheet1.toString().replaceAll("null", ""));

						JSONObject obj1 = new JSONObject(jsonSheet1);

						JSONArray arr1 = obj1.getJSONArray("values");

						int points = 0;

						for (int i = 1; i < arr1.length(); i++) {

							List<String> list = Arrays.asList(((arr.get(i).toString().replace("[", "").replace("]", "")
									.replace("\"", "").replace(".", "").split("\\s*,\\s*"))));

							System.out.println(list);

							if (list.get(0).toLowerCase().contains(user.toLowerCase())) {

								List<String> list1 = Arrays.asList(((arr1.get(i).toString().replace("[", "")
										.replace("]", "").replace("\"", "").replace(".", "").split("\\s*,\\s*"))));

								points += Integer.parseInt(list1.get(0));

							}

						}

						message.reply(message.getAuthor().getMentionTag() + " You have **" + points + "** points");

					}

				}

				if (message.getContent().startsWith("?checkverified")) {

					List<User> usersToCheck = message.getMentions();
					String messageToSend = null;

					for (int i = 0; i < usersToCheck.size(); i++) {

						User userToCheck = usersToCheck.get(i);
						String userToCheckID = userToCheck.getId();

						String checkVerify;
						try {
							checkVerify = checkVerification(userToCheckID);
						} catch (IOException e) {
							e.printStackTrace();
							checkVerify = ("Error: " + e.toString());
						}

						if (checkVerify.equals("1")) {

							messageToSend += ("Too many requests. Try again later." + "\n");

						} else if (checkVerify.contains("Error:")) {

							messageToSend += ("An unknown error occurred.\n\n" + checkVerify + "\n");

						} else if (checkVerify.equals("0")) {

							messageToSend += ("User **" + userToCheck.getName() + "** is not verified" + "\n");

						} else {

							messageToSend += ("User **" + userToCheck.getName() + "** is verified" + "\n");

						}

					}

					messageToSend = messageToSend.replaceAll("null", "");

					message.reply(messageToSend);

				}

				if (message.getContent().equalsIgnoreCase("?checkverify")
						|| message.getContent().startsWith("?verify")) {

					String checkVerify;
					try {
						checkVerify = checkVerification(message.getAuthor().getId());
					} catch (IOException e) {
						e.printStackTrace();
						checkVerify = ("Error: " + e.toString());
					}

					if (checkVerify.equals("1")) {

						message.reply("Too many requests. Try again later.");

					} else if (checkVerify.contains("Error:")) {

						message.reply("An unknown error occurred.\n\n" + checkVerify);

					} else if (checkVerify.equals("0")) {

						message.reply(
								"You are not verified. Please visit https://verify.eryn.io/ to verify. You can do ?checkverify to make sure the verification went through.");

					} else {

						message.reply("You are verified under the username **" + checkVerify + "**");

					}

				}

				if (message.getContent().equalsIgnoreCase("?getroleid")) {

					Collection<Role> roleStartList = server.getRoles();
					ArrayList<Role> roleList = new ArrayList<Role>(roleStartList);

					String roleListFinal = "";

					for (int i = 0; i < roleList.size(); i++) {

						Role currentRole = roleList.get(i);

						roleListFinal += "\n" + currentRole;

					}

					message.getAuthor().sendMessage(roleListFinal.replace("@", ""));
					System.out.println(roleListFinal.replace("@", ""));

				}

				if (message.getContent().equalsIgnoreCase("?rankme")) {

					String checkVerify;
					try {
						checkVerify = checkVerification(message.getAuthor().getId());
					} catch (IOException e) {
						e.printStackTrace();
						checkVerify = ("Error: " + e.toString());
					}

					if (checkVerify.equals("1")) {

						message.reply("Too many requests. Try again later.");

					} else if (checkVerify.contains("Error:")) {

						message.reply("An unknown error occurred.\n\n" + checkVerify);

					} else if (checkVerify.equals("0")) {

						message.reply(
								"You are not verified. Please visit https://verify.eryn.io/ to verify. You can do ?checkverify to make sure the verification went through.");

					} else {

						user = checkVerify;

						userID = httpRequest("https://api.roblox.com/users/get-by-username?username=" + user);

						if (userID.contains("\"Id\"")) {

							int end = userID.indexOf(",");

							userID = userID.substring(6, end);

							userRank = httpRequest(
									"https://www.roblox.com/Game/LuaWebService/HandleSocialRequest.ashx?method=GetGroupRole&playerid="
											+ userID + "&groupid=3052496");

							switch (userRank) {

							case "Awaiting Verification":
								message.reply("Rank found: " + userRank
										+ "\n\nRoles Given: \nNone\n\nNOTE: You are **Awaiting Verification**. Please wait untill your group rank is updated to Labour Lottery");
								api.getCachedUserById("193839548122267649").sendMessage(user);

								break;
							case "Labour Lottery ":
								message.reply("Rank found: " + userRank
										+ "\n\nRoles Given: \n-Labour Lottery\n\nNOTE: You are a **Labour Lottery**. Please organize with a Senior Inspector+ to recieve a trial.");
								message.getAuthor().sendMessage("Rank found: " + userRank
										+ "\n\nRoles Given: \n-Labour Lottery\n\nNOTE: You are a **Labour Lottery**. Please organize with a Senior Inspector+ to recieve a trial.");
								Role[] LabourLotteryRoles = { server.getRoleById(LabourLottery) };
								server.updateRoles(message.getAuthor(), LabourLotteryRoles);
								break;
							case "Apprentice Inspector":
								message.reply("Rank found: " + userRank
										+ "\n\nRoles Given: \n-Apprentice Inspector\n\nNOTE: Apprentice Inspector is the first of the Inspector ranks. As an apprentice Inspector you should spend all of your time in the booth. Try to watch your fellow higher ranks and learn from them. At this rank, dedication to your job is very important.");
								Role[] ApprenticeRoles = { server.getRoleById(Apprentice) };
								server.updateRoles(message.getAuthor(), ApprenticeRoles);
								message.getAuthor().updateNickname(server, user);
								break;
							case "Junior Inspector":
								message.reply("Rank found: " + userRank
										+ "\n\nRoles Given: \n-Junior Inspector\n\nNOTE: As a Junior Inspector, you have been recognized for your hard work. Staying active is important. Try to help your fellow Apprentice Inspectors and teach them things you have learned from your times as an Apprentice. Offer to step in the booth and take over for your Inspectors. Keep focused and dedicated, knowing that you are almost the rank of Inspector.");
								Role[] JuniorRoles = { server.getRoleById(Junior) };
								server.updateRoles(message.getAuthor(), JuniorRoles);
								message.getAuthor().updateNickname(server, user);
								break;
							case "Inspector":
								message.reply("Rank found: " + userRank
										+ "\n\nRoles Given: \n-Inspector\n\nNOTE: Congratulations, you are the rank of Inspector. You have come a long way from when you first started as Labour Lottery. As an inspector you should be demonstrating the qualities of what it truly means to be in Federation Admissions. Continue to help those below you and offer your assistance to those above you. Take every opportunity to stand out and exemplify your dedication and skills.");
								Role[] InspectorRoles = { server.getRoleById(Inspector) };
								server.updateRoles(message.getAuthor(), InspectorRoles);
								message.getAuthor().updateNickname(server, user);
								break;
							case "Senior Inspector":
								message.reply("Rank found: " + userRank
										+ "\n\nRoles Given: \n-Senior Inspector\n-Trainer\n\n\nNOTE: With the rank of Senior Inspector, comes more responsibility. You are now allowed to train Labour Lottery. Reach out to them and train as many as you can. Remember your goal is for them to pass. Work with them patiently. When a new Senior is promoted, you must teach them how to train. As a Senior Inspector, you are still expected to work in the booth like the ranks below you. If you would like you may attend the Super Staff meeting on the weekend. ");
								Role[] SeniorRoles = { server.getRoleById(Senior), server.getRoleById(Trainer) };
								server.updateRoles(message.getAuthor(), SeniorRoles);
								message.getAuthor().updateNickname(server, user);
								break;
							case "Head Inspector":
								message.reply("Rank found: " + userRank
										+ "\n\nRoles Given: \n-Head Inspector\n-Trainer\n\n\nNOTE: Head Inspector is the highest of the Inspector ranks. After serving lengthy time dedicated to this ministry and displaying the best of qualities, you have earned this title bestowing yourself some intermediated power. You are the highest of the Inspector Ranks. This places you as part of the Super Staff. Each week you will be required to work with your fellow Head Inspectors to acquire the number of how many trainings occurred for that week and of those how many passed. You will report this information at the Super Staff meeting. You are also expected to be listening to all those below you. Help them if you can, or if not, direct them to someone who can. You must remain extremely active in this position. Head Inspectors are the lowest rank in the Super Staff. ");
								Role[] HeadRoles = { server.getRoleById(Head), server.getRoleById(Trainer) };
								server.updateRoles(message.getAuthor(), HeadRoles);
								message.getAuthor().updateNickname(server, "Head Inspector " + user);
								break;
							case "Junior Supervisor":
								message.reply("Rank found: " + userRank
										+ "\n\nRoles Given: \n-Junior Supervisor\n-Trainer\n\n\nNOTE: Junior Supervisors, as the names imply are the lower ranked Supervisors of this Ministry. They enter servers ensuring everyone is doing the right task. From there, they may evaluate Labour Lottery members and give further guidance to Apprentice Inspectors and Junior Inspectors of which will be recommended for the next rank. They are a member of the Super Staff. ");
								Role[] JSRoles = { server.getRoleById(JS), server.getRoleById(Trainer) };
								server.updateRoles(message.getAuthor(), JSRoles);
								message.getAuthor().updateNickname(server, "Jr. Supervisor " + user);
								break;
							case "Supervisor":
								message.reply("Rank found: " + userRank
										+ "\n\nRoles Given: \n-Supervisor\n-Trainer\n\n\nNOTE: Supervisor is the higher tier of the two Supervisor ranks. They are much like a Junior Supervisor, only more experience and higher on the chain of command. Supervisors are to make sure things are running properly in the booth and have a good amount of say in recommendations for promotions. Supervisors are a member of the Super Staff. ");
								Role[] SRoles = { server.getRoleById(Supervisor), server.getRoleById(Trainer) };
								server.updateRoles(message.getAuthor(), SRoles);
								message.getAuthor().updateNickname(server, "Supervisor " + user);
								break;
							case "Chief Inspector":
								message.reply("Rank found: " + userRank
										+ "\n\nRoles Given: \n-Chief Inspector\n-Trainer\n\n\nNOTE: Chiefs are one of the highest roles you can get within admissions. These people have worked long and hard to earn the rank. They have extra permissions, like mod, the ability to close the border, and the ability to promote anyone through the range of inspector. Chiefs are hand picked by the HRs, as they are involved in many decisions. They are the highest rank of the Super Staff. ");
								Role[] ChiefRoles = { server.getRoleById(Chief), server.getRoleById(Trainer) };
								server.updateRoles(message.getAuthor(), ChiefRoles);
								message.getAuthor().updateNickname(server, "Chief " + user);
								break;
							case "Guest":
								message.reply("User **" + user
										+ "** is not in the group. If you recieved an acceptance message, then you will need to join the group and re-submit your application.\n\nhttps://www.roblox.com/Groups/Group.aspx?gid=3052496");
								break;
							default:
								message.reply("Rank **" + userRank + "** is not recongnized. Please contact a Deputy+");

							}

						} else {

							message.reply("User **" + user + "** could not be found. Contact a Deputy+");

						}

					}

				}

			}
		});

	}

	public static String httpRequest(String urlString) {

		String output = null;

		try {
			URL url;
			url = new URL(urlString);
			URLConnection urlconnection = url.openConnection();
			urlconnection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null)
				output = inputLine;
			if (output.length() <= 50) {
				System.out.println("Request: " + url + "\nResult: " + output);
			} else {
				System.out.println("Request: " + url + "\nResult: Too long");
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;

	}

	public static String checkVerification(String Id) throws IOException {

		if (httpTimeout < 20) {

			String verifyString = null;
				URL url;
				url = new URL("https://verify.eryn.io/api/user/" + Id);
				URLConnection urlconnection = url.openConnection();
				urlconnection.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
				try {
				BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
				String inputLine;

					while ((inputLine = in.readLine()) != null)
					verifyString = inputLine;
					System.out.println("Request: " + url + "\nResult: " + verifyString);
					System.out.println("Request: " + url + "\nResult: Too long");
					
					in.close();
				} catch (java.io.FileNotFoundException e) {
					
					return "0";
					
				} catch (java.io.IOException e) {
					
					if (e.toString().contains("429")) {
						
						return "1";
						
					} else {
						
						return "";
						
					}
					
				} catch (Exception e) {
					
					return "";
					
				}

			JSONObject json = new JSONObject(verifyString);

			String status = json.getString("status");

			if (status.equals("ok")) {

				String username = json.getString("robloxUsername");

				return username; // Verified

			}

		} else {

			return "1";

		}
		return "Error";

	}

}
