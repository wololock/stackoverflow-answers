import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

final class LastCommit {

    public static void main(String[] args) throws IOException, GitAPIException {
        final Git git = Git.open(new File("."));
        final List<Ref> branches = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
        final RevWalk revWalk = new RevWalk(git.getRepository());

        branches.stream()
                .map(branch -> {
                    try {
                        return revWalk.parseCommit(branch.getObjectId());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .sorted(Comparator.comparing((RevCommit commit) -> commit.getAuthorIdent().getWhen()).reversed())
                .findFirst()
                .ifPresent(commit -> {
                    System.out.printf("%s: %s (%s)%n", commit.getAuthorIdent().getWhen(), commit.getShortMessage(), commit.getAuthorIdent().getName());
                });
    }
}
